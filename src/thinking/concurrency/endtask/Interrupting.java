package thinking.concurrency.endtask;
import java.util.concurrent.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
class SleepBlock implements Runnable{
    public void run(){
        try{
            TimeUnit.SECONDS.sleep(100);
        }catch(InterruptedException e){
            System.out.println("SleepBlock interrupted");
        }
        System.out.println("Exiting SleepBlock.run()");
    }
}
class IOBlock implements Runnable{
    private InputStream in;
    public IOBlock(InputStream is){in=is;}
    public void run(){
        try{
            System.out.println("waiting for read...");
            in.read();
        }catch(IOException e){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from block I/O");
            }else{
                throw new RuntimeException();
            }
        }
        System.out.println("Exiting IOBlock.run()");
    }
}
class SynchronizedBlock implements Runnable{
    public synchronized void f(){
        while(true){
            Thread.yield();
        }
    }
    public SynchronizedBlock(){
        new Thread(){
            public void run(){
                f();
            }
        }.start();
    }
    public void run(){
        System.out.println("try to calling f()");
        f();
        System.out.println("Exiting SynchronizedBlock.run()");
    }
}
public class Interrupting{
    private static ExecutorService es=Executors.newCachedThreadPool();
    static void test(Runnable r) throws InterruptedException{
        Future<?> f=es.submit(r);
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("Interrupting:"+r.getClass().getName());
        f.cancel(true);
        System.out.println("Interruption send to "+r.getClass().getName());
    }
    public static void main(String[] args) throws Exception{
        test(new SleepBlock());
        test(new IOBlock(System.in));
        test(new SynchronizedBlock());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with exit(0)");
        System.exit(0);
    }
}