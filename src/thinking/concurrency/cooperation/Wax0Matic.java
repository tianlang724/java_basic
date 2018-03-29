package thinking.concurrency.cooperation;
import java.util.concurrent.*;
class Car2{
    private boolean waxon=false;
    public synchronized void waxed(){
        waxon=true;
        notifyAll();
    } 
    public synchronized void buffed(){
        waxon=false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException{
        while(waxon==false)
            wait();
    }
    public synchronized void waitForBuffing() throws InterruptedException{
        while(waxon==true)
            wait();
    }
}
class WaxOn2 implements Runnable{
    private Car2 car;
    public WaxOn2(Car2 c){car=c;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println("Wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch(InterruptedException e){
            System.out.println("Exiting by interrupt");
        }
        System.out.println("Ending waxon...");
    }
}
class Waxoff2 implements Runnable{
    private Car2 car;
    public Waxoff2(Car2 c){car=c;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("wax off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch(InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending waxoff...");
      
    }
}
public class Wax0Matic{
    public static void main(String[] args) throws Exception{
//        Car2 car=new Car2();
//        ExecutorService es=Executors.newCachedThreadPool();
//        es.execute(new Waxoff(car));
//        es.execute(new WaxOn(car));
//        TimeUnit.SECONDS.sleep(5);
//        es.shutdownNow();
    }
}