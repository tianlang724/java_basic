package thinking.concurrency.basic;
import  java.util.*;
import java.util.concurrent.*;
public class LiftOff implements Runnable
{
    protected int countDown=10;
    private static int taskCount=0;
    private final int id=taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown)
    {
        this.countDown=countDown;
    }
    public String status()
    {
        return "#"+id+"("+(countDown>0?countDown:"Liftoff!")+"),";
    }
    public void run()
    {
        //testYield();
        testSleep();
    }
    void testYield(){
        while(countDown-->0){
            System.out.print(status());
            Thread.yield();
        }
    }
    void testSleep(){
        try{
            while(countDown-->0){
                    System.out.print(status());
                    TimeUnit.MICROSECONDS.sleep(100);
                }

        }catch(InterruptedException e){}       
    }
    public static void main(String[] args)
    {
        for(int i=0;i<5;i++)
        {
            new Thread(new LiftOff()).start();
        }
        System.out.println("waiting for LiftOff");
    }
}