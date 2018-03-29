package thinking.concurrency.deadlock;
import java.util.concurrent.*;

import thinking.concurrency.deadlock.Chopstick;
public class DeadlockPhilosopher{
    public static void main(String[] args) throws Exception{
        int factor=5;
        if(args.length>0)
            factor=Integer.parseInt(args[0]);
        int size=5;
        if(args.length>1)
            size=Integer.parseInt(args[1]);
        ExecutorService es=Executors.newCachedThreadPool();
        Chopstick[] sticks=new Chopstick[size];
        for(int i=0;i<size;i++)
            sticks[i]=new Chopstick();
        for(int i=0;i<size;i++)
            es.execute(new Philosopher(sticks[i],sticks[(i+1)%size],i,factor));
        if(args.length==3&&args[2].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else{
            System.out.println("press enter to quit");
            System.in.read();
        }
        es.shutdownNow();
    }
}