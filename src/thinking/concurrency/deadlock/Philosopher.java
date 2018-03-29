package thinking.concurrency.deadlock;
import java.util.concurrent.*;

import thinking.concurrency.deadlock.Chopstick;

import java.util.*;
public class Philosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int factor;
    private Random rand=new Random(47);
    private void pause ()throws InterruptedException{
        if(factor==0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(factor*50));
    }
    public Philosopher(Chopstick l,Chopstick r,int id,int fac){
        left=l;
        right=r;
        this.id=id;
        this.factor=fac;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();
                System.out.println(this+" grabbing left");
                left.take();
                right.take();
                System.out.println(this+" grabbing right");
                System.out.println(this+" eating");
                pause();
                left.drop();
                right.drop();
            }
        }catch(InterruptedException e){
            System.out.println("this"+" interrupt");
        }
    }
    public String toString(){
        return  "Philosopher "+id;
    }
}
