package thinking.concurrency.optimizing;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
public class ReaderWriterList<T>{
    private ArrayList<T> lockedList;
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);
    public ReaderWriterList(int size, T initialValue){
        lockedList=new ArrayList<>(Collections.nCopies(size, initialValue));
    }
    public T set(int index,T value){
        Lock wLock=lock.writeLock();
        wLock.lock();
        try{
            return lockedList.set(index,value);
        }finally{
            wLock.unlock();
        }
    }
    public T get(int index){
        Lock rLock=lock.readLock();
        rLock.lock();
        try{
            if(lock.getReadLockCount()>1){
                System.out.println("ReadLockCount="+lock.getReadLockCount());
            }
            return lockedList.get(index);
        }finally{
            rLock.unlock();
        }
    }
    public static void main(String[] args){
        new ReaderWriterListTest(30, 1);
    }
}
class ReaderWriterListTest{
    ExecutorService es=Executors.newCachedThreadPool();
    private final static int SIZE=100;
    private static Random rand=new Random(47);
    private ReaderWriterList<Integer> list=new ReaderWriterList<Integer>(SIZE, 0);
    private class Writer implements Runnable{
        public void run(){
       try{
        for(int i=0;i<20;i++){
            list.set(i, rand.nextInt());
            TimeUnit.MILLISECONDS.sleep(100);
        }
       }catch(InterruptedException e){}
            System.out.println("Writer finished,shuting down");
            es.shutdownNow();
        }

    }
    private class Reader implements Runnable{
        public void run(){
            try{
                while(!Thread.interrupted()){
                    for(int i=0;i<SIZE;i++){
                        list.get(i);
                        TimeUnit.MICROSECONDS.sleep(100);
                    }
                }
            }catch(InterruptedException e){}
        }
    }
    public ReaderWriterListTest(int readers,int writers){
        for(int i=0;i<readers;i++)
            es.execute(new Reader());
        for(int i=0;i<writers;i++)
            es.execute(new Writer());
    }
}