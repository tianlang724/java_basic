package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;
import thinking.generics.genericsinterface.*;

class ExchangerProductor<T> implements Runnable{
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    ExchangerProductor(Exchanger<List<T>> exchg,Generator<T> gen,List<T> list){
        exchanger=exchg;
        generator=gen;
        holder=list;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                for(int i=0;i<ExchangerDemo.size;i++){
                    holder.add(generator.next());
                }
                System.out.println("productor list before exchange:"+holder);
                holder=exchanger.exchange(holder);
                System.out.println("productor list after exchange:"+holder);
            }
        }catch(InterruptedException e){}
    }
}
class ExchangerConsumer<T> implements Runnable{
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger ex,List<T> list){
        exchanger=ex;
        holder=list;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println("consumer list before exchange:"+holder);
                holder=exchanger.exchange(holder);
                System.out.println("consumer list after exchange:"+holder);
                for(T x:holder){
                    value=x;
                    holder.remove(x);
                }
            }
        }catch(InterruptedException e){}
        System.out.println("Final value:" +value);
    }
}
public class ExchangerDemo{
    static int size=10;
    static int delay=5;
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        Exchanger<List<Fat>> ex=new Exchanger<>();
        List<Fat> productorlist=new CopyOnWriteArrayList<>();
        List<Fat> consumerlist=new CopyOnWriteArrayList<>();
        es.execute(new ExchangerProductor<Fat>(ex, BasicGenerator.create(Fat.class),productorlist));
        es.execute(new ExchangerConsumer<Fat>(ex,consumerlist));
        TimeUnit.SECONDS.sleep(delay);
        es.shutdownNow();
    }
}