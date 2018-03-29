package thinking.concurrency.endtask;
import java.util.*;
import java.util.concurrent.*;

class NeedsCleanUp{
    private final int id;
    public NeedsCleanUp(int idn){
        id=idn;
        System.out.println("NeedsCleanup "+id);
    }
    public void cleanup(){
        System.out.println("Cleanup "+id);
    }
}
class Blocked3 implements Runnable{
    private volatile double d=0;
    public void run(){
        try{
            while(!Thread.interrupted()){
                NeedsCleanUp n1=new NeedsCleanUp(1);
                try{
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanUp n2=new NeedsCleanUp(2);
                    try{
                        System.out.println("calculate....");
                        for(int i=0;i<25000;i++)
                            d=d+(Math.PI+Math.E)/d;
                        System.out.println("finished calculate");
                    }finally{
                        n2.cleanup();
                    }
                }finally{
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while");
        }catch(InterruptedException e){
            System.out.println("Exiting via Interruption");
        }
    }
}
public class InterruptingIdiom{
    public static void main(String[] args) throws Exception{
        if(args.length!=1){
            System.out.println("usage:delay time");
            System.exit(0);
        }
        Thread t=new Thread (new Blocked3());
        t.start();
        TimeUnit.SECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }
}