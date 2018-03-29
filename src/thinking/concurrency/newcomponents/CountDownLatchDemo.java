package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;

class TaskPartion implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private static Random random=new Random(47);
    public final CountDownLatch latch;
    TaskPartion(CountDownLatch cdt){latch=cdt;}
    public void run(){
        try{
            doWork();
            latch.countDown();
        }catch(InterruptedException e){

        }
    }
    public void doWork() throws InterruptedException{
        TimeUnit.MICROSECONDS.sleep(random.nextInt(2000));
        System.out.println(this+" completed");
    }
    public String toString(){
        return  String.format("%1$-3d", id);
    }
}
class WaitingTask implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final CountDownLatch latch;
    public WaitingTask(CountDownLatch cdt){latch=cdt;}
    public void run(){
        try{
            latch.await();
            System.out.println("latch barrier passed for "+this);
        }catch(InterruptedException e){
            System.out.println(this+" interrput");
        }
    }
    public String toString(){
        return String.format("watingTask %1$-3d", id);
    }
}
public class CountDownLatchDemo{
    static final int SIZE=100;
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        CountDownLatch latch=new CountDownLatch(SIZE);
        for(int i=0;i<SIZE;i++){
            es.execute(new TaskPartion(latch));
        }
        for(int i=0;i<10;i++){
            es.execute(new WaitingTask(latch));
        }
        System.out.println("launched all task");
        es.shutdown();
    }
}