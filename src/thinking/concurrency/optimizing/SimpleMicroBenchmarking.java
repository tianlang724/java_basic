package thinking.concurrency.optimizing;
import java.util.concurrent.locks.*;
abstract class Incrementable{
    protected long counter=0;
    public abstract void increment();
}
class SynchronizingTest extends Incrementable{
    public synchronized void increment(){++counter;}
}
class LockingTest extends Incrementable{
    private Lock lock=new ReentrantLock();
    public void increment(){
        lock.lock();
        try{
            ++counter;
        }finally{
            lock.unlock();
        }
    }
}
public class SimpleMicroBenchmarking{
    static long test(Incrementable incre){
        long start=System.nanoTime();
        for(int i=0;i<100000000L;i++)
            incre.increment();
        return System.nanoTime()-start;
    }
    public static void main(String[] args){
        long synchTime=test(new SynchronizingTest());
        long lockTime=test(new LockingTest());
        System.out.printf("synchronized:%1$10d\n",synchTime);
        System.out.printf("lock:%1$10d\n",lockTime);
    }
}