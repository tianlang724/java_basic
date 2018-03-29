package thinking.concurrency.optimizing;
import java.util.concurrent.*;
import java.util.*;
public class ActiveObjectDemo{
    private ExecutorService es=Executors.newSingleThreadExecutor();
    private Random rand=new Random(47);
    private void pause(int factor){
        try{
            TimeUnit.MICROSECONDS.sleep(100+rand.nextInt(factor));
        }catch(InterruptedException e){}
    }
    public Future<Integer> calculateInt(final int x,final int y){
        return es.submit(new Callable<Integer>() {
            public Integer call(){
                System.out.println("starting "+x+" "+y);
                pause(500);
                return x+y;
            }
        });
    }
    public Future<Float> calculateFloat(final float x,final float y){
        return es.submit(new Callable<Float>() {
            public Float call(){
                System.out.println("starting "+x+" "+y);
                pause(500);
                return x+y;
            }
        });
    }
    public void shutdown(){es.shutdown();}
    public static void main(String[] args){
        ActiveObjectDemo demo=new ActiveObjectDemo();
        List<Future<?>> results=new CopyOnWriteArrayList<Future<?>>();
        for(float f=0.0f;f<1.0f;f+=0.2f){
            results.add(demo.calculateFloat(f,f));
        }
        for(int i=0;i<5;i++){
            results.add(demo.calculateInt(i,i));
        }
        System.out.println("All synch calls end");
        while(results.size()>0){
            for(Future<?> f:results){
                if(f.isDone()){
                    try{
                        System.out.println(f);
                    }catch(Exception e){
                        throw new RuntimeException(e);
                    }
                }
                results.remove(f);
            }
        }
        demo.shutdown();
    }

}