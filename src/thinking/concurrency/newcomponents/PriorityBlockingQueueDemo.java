package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
class PriorityTask implements Runnable,Comparable<PriorityTask>{
    private Random random=new Random(47);
    private static int counter=0;
    private final int id=counter++;
    private final int priority;
    protected static List<PriorityTask> sequeue=new ArrayList<>();
    public PriorityTask(int p){
        priority=p;
        sequeue.add(this);
    }
    public int compareTo(PriorityTask arg){
        return priority<arg.priority?1:(priority>arg.priority?-1:0);
    }
    public void run(){
        try{
            TimeUnit.MICROSECONDS.sleep(random.nextInt(250));
        }catch(InterruptedException e){}
        System.out.println(this);
    }
    public String toString(){
        return String.format("[%1$-3d]",priority)+" Task "+id;
    }
    public String summary(){
        return "("+id+")"+":"+priority;
    }
    public static class EndSential extends PriorityTask{
        private ExecutorService es;
        public EndSential(ExecutorService e){
            super(-1);
            es=e;
        }
        public void run(){
            for(PriorityTask pt:sequeue){
                System.out.println(pt.summary());
            }
            System.out.println(this+" Calling shutdownnow");
            es.shutdownNow();
        }
    }
}
class PriorityTaskProducer implements Runnable{
    private Random rand=new Random(47);
    private ExecutorService es;
    private Queue<Runnable> queue;
    public PriorityTaskProducer(Queue<Runnable> q,ExecutorService es){
        queue=q;
        this.es=es;
    }
    public void run(){
        for(int i=0;i<20;i++){
            queue.add(new PriorityTask(rand.nextInt(10)));
            Thread.yield();
        }
        try{
            for(int i=0;i<10;i++){
                TimeUnit.MICROSECONDS.sleep(250);
                queue.add(new PriorityTask(10));
            }
            for(int i=0;i<5;i++){
                queue.add(new PriorityTask(i));
            }
            queue.add(new PriorityTask.EndSential(es));
        }catch(InterruptedException e){}
        System.out.println("Finished PriorityTaskProducer");
    }
}
class PriorityCosumer implements Runnable{
    private PriorityBlockingQueue<Runnable> q;
    public PriorityCosumer( PriorityBlockingQueue<Runnable>q){this.q=q;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                q.take().run();
            }
        }catch(InterruptedException e){}
        System.out.println("Finished PriorityConsumer");
    }

}
public class PriorityBlockingQueueDemo{
    public static void main(String[] args){
        Random rand=new Random(47);
        ExecutorService es=Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue=new PriorityBlockingQueue<>();
        es.execute(new PriorityCosumer(queue));
        es.execute(new PriorityTaskProducer(queue, es));
    }
}