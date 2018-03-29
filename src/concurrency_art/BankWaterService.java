package concurrency_art;
import java.util.Map.Entry;
import java.util.concurrent.*;
public class BankWaterService implements Runnable
{
    private CyclicBarrier c =new CyclicBarrier(4);
    private Executor executor=Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String,Integer> sheet=new ConcurrentHashMap<>();
    private void count(){
        for(int i=0;i<4;i++){
            executor.execute(new Runnable(){
                public void run(){
                    sheet.put(Thread.currentThread().getName(),1);
                try{
                    c.await();
                }catch(InterruptedException|BrokenBarrierException e){
                    e.printStackTrace();
                }
            }
            });
        }
    }
    public void run(){
        int result=0;
        for(Entry<String,Integer> sh:sheet.entrySet()){
            result +=sh.getValue();
        }
        sheet.put("result",result);
        System.out.println(result);
    }
    public static void main(String[] args){
        BankWaterService bank=new BankWaterService();
        bank.count();
    }
}