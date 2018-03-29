package thinking.concurrency.optimizing;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import java.util.*;
public class FastSimulation{
    static final int N_ELEMENTS=5000;
    static final int N_GENES=30;
    static final int N_EVOLVERS=50;
    static final AtomicInteger[][] GRID=new AtomicInteger[N_ELEMENTS][N_ELEMENTS];
    static Random rand=new Random(47);
    static class Evolver implements Runnable{
        public void run(){
            while(!Thread.interrupted()){
                int element=rand.nextInt(N_ELEMENTS);
                for(int i=0;i<N_GENES;i++){
                    int previous=element-1;
                    if(previous<0) previous=N_ELEMENTS-1;
                    int next=element+1;
                    if(next>=N_ELEMENTS) next=0;
                    int oldVaule=GRID[element][i].get();
                    int newValue=GRID[previous][i].get()+GRID[next][i].get()+oldVaule;
                    newValue/=3;
                    if(!GRID[element][i].compareAndSet(oldVaule,newValue)){
                        System.out.println("Old value chaged from "+oldVaule);
                    }
                    System.out.println("ok "+i);
                }
                System.out.println("ok");
            }
        }
    }
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<N_ELEMENTS;i++){
            for(int j=0;j<N_ELEMENTS;j++){
                GRID[i][j]=new AtomicInteger(rand.nextInt(1000));
            }
        }
        for(int i=0;i<N_EVOLVERS;i++){
            es.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}