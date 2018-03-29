package thinking.concurrency.shareresources;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
class Pair{
    private int x,y;
    public Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Pair(){this(0,0);}
    public int getX(){return x;}
    public int getY(){return y;}
    public void incrementX(){x++;}
    public void incrementY(){y++;}
    public String toString(){
        return "x:"+x+" y:"+y;
    }
    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException(){
            super("Pair value not equal:"+Pair.this);
        }
    }
    public void checkState(){
        if(x!=y){
            throw new PairValuesNotEqualException();
        }
    }
}
abstract class PairManager{
    AtomicInteger checkCounter=new AtomicInteger(0);
    protected Pair p=new Pair();
    private List<Pair> storage=Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair(){
        return new Pair(p.getX(),p.getY());
    }
    protected void store(Pair p){
        storage.add(p);
        try{
            TimeUnit.MICROSECONDS.sleep(50);
        }catch(InterruptedException e){
        }
    }
    public abstract void increment();
}
class PairManager1 extends PairManager{
    public synchronized void increment(){
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}
class PairManager2 extends PairManager{
    public void increment(){
        Pair temp;
        synchronized(this){
            p.incrementX();
            p.incrementY();
            temp=getPair();
        }
        store(temp);
    }
}
class PairManipulator implements Runnable{
    private PairManager pm;
    public PairManipulator(PairManager pm){
        this.pm=pm;
    }
    public void run(){
        while(true){
            pm.increment();
        }
    }
    public String toString(){
        return "Pair:"+pm.getPair()+" checkCount:"+pm.checkCounter.get();
    }
}
class PairChecker implements Runnable{
    private PairManager pm;
    public PairChecker(PairManager pm){
        this.pm=pm;
    }
    public void run(){
        while(true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
public class CriticalSection{
    static void testApproches(PairManager pm1,PairManager pm2){
        ExecutorService es=Executors.newCachedThreadPool();
        PairManipulator p1=new PairManipulator(pm1);
        PairManipulator p2=new PairManipulator(pm2);
        PairChecker pc1=new PairChecker(pm1);
        PairChecker pc2=new PairChecker(pm2);
        es.execute(p1);
        es.execute(p2);
        es.execute(pc1);
        es.execute(pc2);
        try{
            TimeUnit.MICROSECONDS.sleep(500);
        }catch(InterruptedException e){
            System.out.println("sleep interrupted");
        }
        System.out.println("p1:"+p1+" ,p2:"+p2);
        System.exit(0);
    }
    public static void main(String[] args){
        PairManager pm1=new PairManager1();
        PairManager pm2=new PairManager2();
        testApproches(pm1, pm2);
    }
}