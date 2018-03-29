package thinking.concurrency.shareresources;
import java.util.concurrent.*;
public class AtomicTest implements Runnable{
    private  int i=0;
    public synchronized int getValue(){return i;}
    public synchronized void evenIncreament(){i++;i++;}
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
