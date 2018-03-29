package thinking.concurrency.optimizing;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

abstract class Accumulator{
    public static long cycles=50000L;
    private static final int N=4;
    public static ExecutorService es=Executors.newFixedThreadPool(N*2);
    private static CyclicBarrier barriers=new CyclicBarrier(N*2+1);
    protected volatile int index=0;
    protected volatile long value;
    protected long duration=0;
    protected final static int SIZE=10000;
    protected String id="error";
    protected static int[] preLoaded=new int[SIZE+10];
    static {
        Random rand=new Random(47);
        for(int i=0;i<SIZE;i++)
            preLoaded[i]=rand.nextInt();
    }
    public abstract void accumulate();
    public abstract long read();
    private class Modifider implements Runnable{
        public void run(){
            for(long i=0;i<cycles;i++){
                accumulate();  
            }
            try{
                barriers.await();
            }catch(Exception e){
                throw new  RuntimeException();
            }
        }
    }
    private class Reader implements Runnable{
        private volatile long value;
        public void run(){
            for(long i=0;i<cycles;i++)
                value=read();
            try{
                barriers.await();
            }catch(Exception e){
                throw new RuntimeException();
            }
        }
    }
    public void timedTest(){
        long start=System.nanoTime();
        for(int i=0;i<N;i++){
            es.execute(new Modifider());
            es.execute(new Reader());
        }
        try{
            barriers.await();
        }catch(Exception e){
            throw new RuntimeException();
        }
        duration=System.nanoTime()-start;
        System.out.printf("%-13s:%13d\n",id,duration);
    }
    public static void report(Accumulator acc1,Accumulator acc2){
        //System.out.printf("%-22s:%.2f\n"+acc1.id+"/"+acc2.id,(double)(acc1.duration/acc2.duration));
        System.out.print(acc1.id+"/"+acc2.id+" ");
        System.out.println((double)(acc1.duration/acc2.duration));
    }
}
class BaseLine extends Accumulator{
    {id="BaseLine";}
    public void accumulate(){
        value+=preLoaded[index++];
        if(index>=SIZE) index=0;
    }
    public  long read(){
        return value;
    }
}
class SynchronizedTest extends Accumulator{
    {id="synchronized";}
    public synchronized void accumulate(){
        value+=preLoaded[index++];
        if(index>=SIZE) index=0;
    }
    public synchronized  long read(){
        return value;
    }
}
class LockTest extends Accumulator{
    {id="lock";}
    private Lock lock=new ReentrantLock();
    public void accumulate(){
        lock.lock();
        try{
            value+=preLoaded[index++];
            if(index>=SIZE) index=0;
        }finally{
            lock.unlock();
        }
    }
    public long read(){
        lock.lock();
        try{
            return value;
        }finally{
            lock.unlock();
        }
    }
}
class AtomicTest extends Accumulator{
    {id="Atomic";}
    private AtomicInteger index=new AtomicInteger(0);
    private AtomicLong value=new AtomicLong(0);
    public void accumulate(){
        int i=index.getAndIncrement();
        if(i>=SIZE) {
            index.set(0);
            i=0;
        }
        value.getAndAdd(preLoaded[i]);
    }
    public long read(){return value.get();}
}
public class SynchronizationComparsions{
    static BaseLine baseLine=new BaseLine();
    static SynchronizedTest synch=new SynchronizedTest();
    static LockTest lock=new LockTest();
    static AtomicTest atomic=new AtomicTest();
    static void test(){
        System.out.println("==============================");
        System.out.printf("%-12s:%13d\n","Cycles",Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        atomic.timedTest();
        lock.timedTest();
        Accumulator.report(synch, baseLine);
        Accumulator.report(lock, baseLine);
        Accumulator.report(atomic, baseLine);
        Accumulator.report(synch, lock);
        Accumulator.report(synch, atomic);
        Accumulator.report(lock, atomic);
    }
    public static void main(String[] args){
        int interation=5;
        System.out.println("Warm up");
        baseLine.timedTest();
        for(int i=0;i<interation;i++){
            test();
            Accumulator.cycles*=2;
        }
        Accumulator.es.shutdown();
    }
}