package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
class DelayedTask implements Runnable,Delayed{
    private static int counter=0;
    private final int id=counter++;
    private final int delay;
    private final long tigger;
    protected static List<DelayedTask> sequeue=new ArrayList<>();
    public DelayedTask(int delayMilliseconds){
        delay=delayMilliseconds;
        tigger=System.nanoTime()+NANOSECONDS.convert(delay,MILLISECONDS);
        sequeue.add(this);
    }
    public long getDelay(TimeUnit unit){
        return unit.convert(tigger-System.nanoTime(),NANOSECONDS);
    }
    public int compareTo(Delayed arg){
        DelayedTask that=(DelayedTask)arg;
        if(tigger<that.tigger) return -1;
        if(tigger>that.tigger) return 1;
        return 0;
    }
    public void run(){
        System.out.println(this+ " ");
    }
    public String toString(){
        return String.format("[%1$-4d]", delay)+"Task "+id;
    }
    public String summary(){
        return "("+id+":"+delay+")";
    }
}
class EndSentinel extends DelayedTask{
    private ExecutorService es;
    public EndSentinel(int delay,ExecutorService es){
        super(delay);
        this.es=es;
    }
    public void run(){
        for(DelayedTask dt:sequeue)
            System.out.println(dt.summary());
        System.out.println(this+"Calling shutdown now");
        es.shutdownNow();
    }  
}
class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;
    public DelayedTaskConsumer(DelayQueue q){
        this.q=q;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                q.take().run();
            }
            }catch(InterruptedException e){
                
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
public class DelayQueueDemo{
    public static void main(String[] args) throws Exception{
        Random random=new Random(47);
        ExecutorService es=Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue=new DelayQueue<>();
        for(int i=0;i<20;i++){
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        queue.add(new EndSentinel(5000, es));
        es.execute(new DelayedTaskConsumer(queue));
    }
}