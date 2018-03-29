package thinking.concurrency.cooperation;
import java.util.concurrent.*;
import java.util.*;
class Toast{
    public enum Status{DAY,BUTTERED,JAMMED};
    private Status status=Status.DAY;
    private final int id;
    public Toast(int id){this.id=id;}
    public void butter(){status=Status.BUTTERED;}
    public void jam(){status=Status.JAMMED;}
    public Status getStatus(){return status;}
    public int getId(){return id;}
    public String toString(){
        return "Toast "+id+":"+status;
    }
}
class ToastQueue extends LinkedBlockingDeque<Toast>{}
class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count=0;
    private Random rand=new Random(47);
    public Toaster(ToastQueue tq){toastQueue=tq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(500));
                Toast t=new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);

            }
        }catch(InterruptedException e){
            System.out.println("Toaster interrupt");
        }
        System.out.println("Toaster off");
    }
}
class Butterer implements Runnable{
    private ToastQueue dryQueue,butteredQueue;
    public Butterer(ToastQueue dq,ToastQueue bq){dryQueue=dq;butteredQueue=bq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t=dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);

            }
        }catch(InterruptedException e){
            System.out.println("Butterer interrupt");
        }
        System.out.println("Butterer off");
    }
}
class Jammer implements Runnable{
    private ToastQueue butteredQueue,finishedQueue;
    public Jammer(ToastQueue bq,ToastQueue fq){butteredQueue=bq;finishedQueue=fq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t=butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);

            }
        }catch(InterruptedException e){
            System.out.println("Jammer interrupt");
        }
        System.out.println("Jammer off");
    }
}
class Eater implements Runnable{
    private ToastQueue finishedQueue;
    private int count=0;
    public Eater(ToastQueue fq){finishedQueue=fq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t=finishedQueue.take();
                if(t.getId()!=count++||t.getStatus()!=Toast.Status.JAMMED){
                    System.out.println(">>>>error:"+t);
                    System.exit(0);
                }else{
                    System.out.println("Chomp "+t);
                }
            }
        }catch(InterruptedException e){
            System.out.println("Eater interrupt");
        }
        System.out.println("Eater off");
    }
}
public class ToastMatic{
    public static void main(String[] args) throws Exception{
        ToastQueue dryQueue=new ToastQueue(),butteredQueue=new ToastQueue(),finishedQueue=new ToastQueue();
        ExecutorService es=Executors.newCachedThreadPool();
        es.execute(new Toaster(dryQueue));
        es.execute(new Butterer(dryQueue,butteredQueue));
        es.execute(new Jammer(butteredQueue,finishedQueue));
        es.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(6);
        es.shutdownNow();
    }
}


