package thinking.concurrency.endtask;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.*;

class Count{
    private int count=0;
    private Random rand=new Random(47);
    public synchronized int increment(){
        int temp=count;
        if(rand.nextBoolean())
            Thread.yield();
        return (count=++temp);
    }
    public synchronized int value(){return count;}
}
class Entrance implements Runnable{
    private static Count count=new Count();
    private static List<Entrance> entrances=new ArrayList<>();
    private int number=0;
    private final int id;
    private static volatile boolean canceled=false;
    public static void cancle(){canceled=true;}
    public Entrance(int id){
        this.id=id;
        entrances.add(this);
    }
    public void run(){
        while(!canceled){
            synchronized(this){
                ++number;
            }
            System.out.println(this+" Total:"+count.increment());
            try{
                TimeUnit.MICROSECONDS.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("stopping "+this);
        }
    }
    public synchronized int getValue(){return number;}
    public String toString(){
        return "Entrance "+id+":"+getValue();
    }
    public static int getTotalCount(){return count.value();}
    public static int sumEntrances(){
        int sum=0;
        for(Entrance e:entrances)
        sum+=e.getValue();
        return sum;
    }
}
public class OrnamentalGarden{
    public static void main(String[] args) throws Exception {
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            es.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancle();
        es.shutdown();
        if(!es.awaitTermination(250, TimeUnit.SECONDS))
            System.out.println("some task are not terminated");
        System.out.println("Total: "+Entrance.getTotalCount());
        System.out.println("sum of entrances:"+Entrance.sumEntrances());
    }
}