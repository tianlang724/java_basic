package thinking.concurrency.newcomponents;
import java.util.concurrent.*;
import java.util.*;
class Horse implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private int strides=0;
    private static Random rand=new Random(47);
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier barrier){this.barrier=barrier;}
    public synchronized int getStrides(){return strides;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized(this){
                    strides+=rand.nextInt(3);
                }
                barrier.await();
            }
        }catch(InterruptedException e){

        }catch(BrokenBarrierException e){
            throw new RuntimeException();
        }
    }
    public String toString(){
        return "Horse "+id;
    }
    public String tracks(){
        StringBuilder s=new StringBuilder();
        for(int i=0;i<getStrides();i++){
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}
public class HorseRace{
    static final int FINISH_LINE=75;
    private List<Horse> horses=new ArrayList<>();
    private ExecutorService es=Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int num,final int pause){
        barrier=new CyclicBarrier(num,new Runnable(){
            public void run(){
                StringBuilder s=new StringBuilder();
                for(int i=0;i<FINISH_LINE;i++)
                    s.append("=");
                    System.out.println(s);
                    for(Horse h:horses){
                        System.out.println(h.tracks());
                    }
                    for(Horse h:horses){
                        if(h.getStrides()>=FINISH_LINE){
                            System.out.println(h+" win");
                            es.shutdownNow();
                            return ;
                        }
                    }
                    try{
                        TimeUnit.MICROSECONDS.sleep(pause);
                    }
                    catch(InterruptedException e){
                        System.out.println("barrier-action sleep interrupt");
                    }
                
            }
        });
        for(int i=0;i<num;i++){
            Horse horse=new Horse(barrier);
            horses.add(horse);
            es.execute(horse);
        }
    }
    public static void main(String[] args){
        int nHorse=7;
        int pause=200;
        if(args.length>0){
            nHorse=Integer.parseInt(args[0]);
        }
        if(args.length>1){
            pause=Integer.parseInt(args[1]);
        }
        new HorseRace(nHorse, pause);
    }
}
