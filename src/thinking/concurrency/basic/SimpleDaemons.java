package thinking.concurrency.basic;
import java.util.concurrent.*;
import java.util.*;
public class  SimpleDaemons implements Runnable
{
    public void run(){
        try{
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" " +this);
            }
        }catch(InterruptedException e){
            System.out.println("sleep() Interruption");
        }
    }
    public static void main(String[] args){
        for(int i=0;i<20;i++){
            Thread daemon=new Thread(new SimpleDaemons());
            //daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All deamon started");
        // try{
        //     TimeUnit.MILLISECONDS.sleep(100);
        // }catch(InterruptedException e){

        // }
    }
}