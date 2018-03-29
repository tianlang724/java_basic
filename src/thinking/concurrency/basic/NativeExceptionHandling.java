package thinking.concurrency.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ExceptionThread implements Runnable{
    public void run(){
        throw new RuntimeException();
    }
}
public class NativeExceptionHandling{
    public static void main(String[] args){
        try{
            ExecutorService exec=Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch(RuntimeException e){
            System.out.println("catch RuntimeException");
        }
    }
}