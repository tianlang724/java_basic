package thinking.concurrency.basic;
import java.util.*;

class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        duration=sleepTime;
        start();
    }
    public void run(){
        try{
            sleep(duration);
        }catch(InterruptedException e){
            System.out.println(getName()+":"+"was interrupted."+"isInterrupted():"+isInterrupted());
            return ;
        }
        System.out.println(getName()+"has awakened");
    }
}
class Joiner extends Thread{
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper=sleeper;
        start();
    }
    public void run(){
        try{
            sleeper.join();
        }catch(InterruptedException e){
            System.out.println("join interruped");
        }
        System.out.println(getName()+"join competed");
    }
}
public class Joining{
    public static void main(String[] args){
        Sleeper one=new Sleeper("One", 1500);
        Sleeper two=new Sleeper("Two",1500);
        Joiner jone=new Joiner("JOne",one);
        Joiner jtwo=new Joiner("JTwo",two);
        one.interrupt();
    }
}