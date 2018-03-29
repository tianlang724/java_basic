package thinking.concurrency.cooperation;
import java.util.concurrent.*;
import java.util.*;
class Blocker{
    synchronized void waitingCall(){
        try{
            while(!Thread.interrupted()){
                wait();
                System.out.print(Thread.currentThread()+" ");
            }
        }catch(InterruptedException e){
            
        }
    }
    synchronized void prod(){notify();}
    synchronized void prodAll(){notifyAll();}
}
class Task implements Runnable{
    static Blocker blocker=new Blocker();
    public void run(){blocker.waitingCall();}
}
class Task2 implements Runnable{
    static Blocker blocker=new Blocker();
    public void run(){blocker.waitingCall();}
}
public class NotifyVsNotifyAll{
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.execute(new Task());
        }
        es.execute(new Task2());
        Timer t=new Timer();
        t.scheduleAtFixedRate(new TimerTask(){
            boolean prod=true;
            public void run() {
                if(prod){
                    System.out.print("\n notify()");
                    Task.blocker.prod();
                    prod=false;
                }else{
                    System.out.print("\n notifyAll()");
                    Task.blocker.prodAll();
                    prod=true;
                }
            }
        }, 400, 400);   
        TimeUnit.SECONDS.sleep(5);
        t.cancel();
        System.out.println("timer canceled");
        TimeUnit.MICROSECONDS.sleep(500);
        System.out.println("Task2.blocker.procAll");
        Task2.blocker.prodAll();
        TimeUnit.MICROSECONDS.sleep(500);
        System.out.println("shut down");
        es.shutdownNow();
    }
}