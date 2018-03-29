package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;
class CheckoutTask implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private Pool<Fat> pool;
    public CheckoutTask(Pool<Fat> pool){this.pool=pool;}
    public void run(){
        try{
            Fat item=pool.checkOut();
            System.out.println(this+" checkout "+item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(" "+this+" checkin "+item);
            pool.checkIn(item);
        }catch(InterruptedException e){}
    }
    public String toString(){
        return "CheckoutTask "+id;
    }
}
public class SemaphoreDemo{
    final static int SIZE=25;
    public static void main(String[] args) throws Exception{
        final Pool<Fat> pool=new Pool<>(Fat.class, SIZE);
        ExecutorService es=Executors.newCachedThreadPool();
        for(int i=0;i<SIZE;i++)
            es.execute(new CheckoutTask(pool));
        System.out.println("All CheckoutTask created");
        List<Fat> list=new ArrayList<>();
        for(int i=0;i<SIZE;i++){
            Fat f=pool.checkOut();
            System.out.print(i+":main() thread checkout");
            f.operation();
            list.add(f);
        }
        Future<?> blocked=es.submit(new Runnable(){
            public void run(){
                try{
                    System.out.println("blocked will checkout");
                    pool.checkOut();
                    System.out.println("blocked will checkout");
                }catch(InterruptedException e){
                    System.out.println("checkout interrupt");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("check in Obeject in"+list);
        for(Fat f:list)
            pool.checkIn(f);
        for(Fat f:list)
            pool.checkIn(f);
        es.shutdown();

    }
}