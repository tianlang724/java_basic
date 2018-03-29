package thinking.concurrency.optimizing;
import java.util.concurrent.*;
import java.util.Random.*;
import thinking.generics.genericsinterface.*;

public abstract class Tester<C>{
    static int testReps=10;
    static int testCycles=1000;
    static int containerSize=1000;
    abstract void startReadersAndWriters();
    abstract C containerInitializer();
    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult=0;
    volatile long readTime=0;
    volatile long writeTime=0;
    CountDownLatch endLatch;
    static ExecutorService es=Executors.newCachedThreadPool();
    Integer[] writeData;
    Tester(String testId,int nReaders,int nWriters){
        this.testId=testId+" r:"+nReaders+" w:"+nWriters;
        this.nReaders=nReaders;
        this.nWriters=nWriters;
        writeData=Generated.array(Integer.class,new RandomGenerator.Integer(),containerSize);
        for(int i=0;i<testReps;i++){
            runTest();
            readTime=0;
            writeTime=0;
        }
    }
    void runTest(){
        endLatch=new CountDownLatch(nReaders+nWriters);
        testContainer=containerInitializer();
        startReadersAndWriters();
        try{
            endLatch.await();
        }catch(InterruptedException e){
            System.out.println("endLatch initerrupt");
        }
        System.out.printf("%-27s %14d %14d\n",testId,readTime,writeTime);
        if(readTime!=0&&writeTime!=0)
            System.out.printf("%-27s %14d\n","readTime+writeTime=",readTime+writeTime);
    }
    abstract class TestTask implements Runnable{
        abstract void test();
        abstract void putResult();
        long duration;
        public void run(){
            long start=System.nanoTime();
            test();
            duration=System.nanoTime()-start;
            synchronized(Tester.this){
                putResult();
            }
            endLatch.countDown();
        }
    }
    public static void initMain(String[] args){
        if(args.length>0)
            testReps=Integer.parseInt(args[0]);
        if(args.length>1)
            testCycles=Integer.parseInt(args[1]);
        if(args.length>2)
            containerSize=Integer.parseInt(args[2]);
        System.out.println("testReps="+testReps+",testCycles="+testCycles+",containerSize="+containerSize);
    }

}