package thinking.concurrency.cooperation;
import java.util.concurrent.*;
class Meal{
    private final int id;
    public Meal(int id){this.id=id;}
    public String toString(){
        return "meal #"+id;
    }
}
class WaitPerson implements Runnable{
    private Restaurant restaurant;
    public WaitPerson(Restaurant res){this.restaurant=res;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized(this){//获取自己锁，等待chef 准备好meal
                    while(restaurant.meal==null)
                        wait();
                }
                System.out.println("waitPerson got "+restaurant.meal);
                synchronized(restaurant.chef){ //获取chef的锁
                    restaurant.meal=null;
                    restaurant.chef.notifyAll();//唤醒chef
                }
            }
        }catch(InterruptedException e){
            System.out.println("waitperson interrupt");
        }
    }
}
class Chef implements Runnable{
    private Restaurant restaurant;
    public Chef(Restaurant res){restaurant=res;}
    private int count=0;
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized(this){//获取自己的锁
                    while(restaurant.meal!=null)
                        restaurant.chef.wait();//调用wait,等待waitPerson处理meal
                }
                if(++count==10){
                    System.out.println("out of food,closing");
                    restaurant.es.shutdownNow();
                }
                System.out.println("order up");
                synchronized(restaurant.waitPerson){ //获取waitPerson的锁
                    System.out.println("chef synchronized");
                    restaurant.meal=new Meal(count);
                    restaurant.waitPerson.notifyAll();//唤醒waitPerson
                }
                System.out.println("chef synchronized");
                TimeUnit.MILLISECONDS.sleep(100);/*没有该句则不会执行到catch,而是在while处退出 */
            }
        }catch(InterruptedException e){
            System.out.println("chef interrupt");
        }
    }
}
/**
 * 这个Restaurant 只有一个waitPerson和一个chef,所以只有两个线程需要同步，
 * 我们可以轻易获取到需要同步线程的对象，如果由多个waitPerson或者多个chef
 * 则使用该方法同步会很麻烦。
 */
public class Restaurant{
    Meal meal;
    ExecutorService es=Executors.newCachedThreadPool();
    WaitPerson waitPerson=new WaitPerson(this);
    Chef chef=new Chef(this);
    public Restaurant(){
        es.execute(chef);
        es.execute(waitPerson);
    }
    public static void main(String[] args){
        new Restaurant();
    }
}