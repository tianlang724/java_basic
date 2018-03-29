package thinking.concurrency.newcomponents;
import java.util.*;
import java.util.concurrent.*;
public class Pool<T> {
    private int size;
    private List<T> items=new ArrayList<>();
    private volatile boolean[] checkOut;
    private Semaphore available;
    public Pool(Class<T> classObject,int size){
        this.size=size;
        checkOut=new boolean[size];
        available=new Semaphore(size,true);
        for(int i=0;i<size;i++){
            try{
                items.add(classObject.newInstance());
            }catch(Exception e){
                throw new RuntimeException();
            }
        }
    }
    public T checkOut() throws InterruptedException{
        available.acquire();
        return getItem();
    }
    public void checkIn(T x){
        if(releaseItem(x))
         available.release();
    }
    private synchronized T getItem(){
        for(int i=0;i<size;i++){
            if(!checkOut[i]){
                checkOut[i]=true;
                return items.get(i);
            }
        }
        return null;
    }
    private synchronized boolean releaseItem(T item){
        int index=items.indexOf(item);
        if(index==-1) return false;
        if(checkOut[index]){
            checkOut[index]=false;
            return true;
        }
        return false;
    }
}