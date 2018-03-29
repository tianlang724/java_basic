package thinking.concurrency.cooperation;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
class Car{
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private boolean waxon=false;
    public void waxed(){
        lock.lock();
        try{
            waxon=true;
            condition.signalAll();
        }finally{
            lock.unlock();
        }
    }
    public void buffed(){
        lock.lock();
        try{
            waxon=false;
            condition.signalAll();
        }finally{
            lock.unlock();
        }
    }
    public void waitForWaxing() throws InterruptedException{
        lock.lock();
        try{
            while(waxon==false){
                condition.await();
            }
        }finally{
            lock.unlock();
        }
    }
    public void waitForBuffing() throws InterruptedException{
        lock.lock();
        try{
            while(waxon==true){
                condition.await();
            }
        }finally{
            lock.unlock();
        }
    }
}
class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car c){car=c;}
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
class Waxoff implements Runnable{
    private Car car;
    public Waxoff(Car c){car=c;}
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
public class Wax0Matic2{
    public static void main(String[] args) throws Exception{
        Car car=new Car();
        ExecutorService es=Executors.newCachedThreadPool();

        es.execute(new WaxOn(car));
        es.execute(new Waxoff(car));
        TimeUnit.SECONDS.sleep(5);
        es.shutdownNow();
    }
}