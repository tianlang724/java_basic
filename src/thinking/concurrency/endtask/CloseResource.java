package thinking.concurrency.endtask;
 import java.net.*;
 import java.util.concurrent.*;
 import java.io.*;
 public class CloseResource{
     public static void main(String[] arg) throws Exception{
         ExecutorService es=Executors.newCachedThreadPool();
         ServerSocket s=new ServerSocket(8080);
         InputStream socketInput=new Socket("localhost",8080).getInputStream();
         es.execute(new IOBlock(socketInput));
         es.execute(new IOBlock(System.in));
         TimeUnit.MICROSECONDS.sleep(100);
         System.out.println("shutting all thread");
         es.shutdownNow();
         TimeUnit.SECONDS.sleep(1);
         System.out.println("Closing "+socketInput.getClass().getName());
         socketInput.close();
         TimeUnit.SECONDS.sleep(1);
         System.out.println("Closing "+System.in.getClass().getName());
         System.in.close();
         System.out.println("Closed");
         //System.exit(0);

     }
 }