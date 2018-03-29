package thinking.concurrency.shareresources;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
public class AtomicIntegerTest implements Runnable{
    private  AtomicInteger i=new AtomicInteger(0);
    public  int getValue(){return i.get();}
    public  void evenIncreament(){i.addAndGet(2);}
    public void run(){
        while(true){
            evenIncreament();
        }
    }
    public static void main(String[] args){
        ExecutorService exec=Executors.newCachedThreadPool();
        AtomicTest at=new AtomicTest();
        exec.execute(at);
        while(true){
            int val=at.getValue();
            if(val%2!=0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
