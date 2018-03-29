package thinking.concurrency.basic;
import  java.util.*;
import java.util.concurrent.*;
public class SimplePriorty implements Runnable
{
    protected int countDown=5;
    private int priorty;
    private volatile double d;
    public SimplePriorty(){}
    public SimplePriorty(int priorty)
    {
        this.priorty=priorty;
    }
    public String toString(){
        return Thread.currentThread()+":"+countDown;
    }
    public void run(){
        Thread.currentThread().setPriority(priorty);
        while(true){
            for(int i=0;i<10000000;i++){
                d+=(Math.PI+Math.E)/(double)i+(Math.PI+Math.E)/(double)i;
                if(i%100000==0)
                Thread.yield();
            }
            System.out.println(this);
            if(--countDown==0) return;
        }
    }
    public static void main(String[] args){
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new SimplePriorty(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorty(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}