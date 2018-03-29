package core.concurrency.blockingqueue;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class BlockingQueueTest{
    private static final int FILE_QUEUW_SIZE=10;
    private static final int SEARCH_THREADS=100;
    private static File DUMY=new File("");
    private static BlockingQueue<File> queue=new ArrayBlockingQueue<>(FILE_QUEUW_SIZE);

    public static void main(String[] args){
        try(Scanner in=new Scanner(System.in))
        {
            System.out.print("Enter base directory(e.g. /opt/jdk1.8.0/src):");
            String directory=in.nextLine();
            System.out.print("Enter a keyword(e.g. volatile:");
            String keyword=in.nextLine();

            Runnable enumerator=()->{
                try
                {
                    enumerate(new File(directory));
                    queue.put(DUMY);
                }
                catch(InterruptedException e)
                {

                }
            };
            new Thread(enumerator).start();
            for(int i=0;i<=SEARCH_THREADS;i++){
                Runnable searcher=()->{
                    try
                    {
                        boolean done=false;
                        while(!done)
                        {
                            File file=queue.take();
                            if(file==DUMY){
                                queue.put(file);
                                done=true;
                            }
                            else search(file,keyword);
                        }
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch(InterruptedException e)
                    {

                    }
                };
                new Thread(searcher).start();
            }
        }
    }
    public static void enumerate(File directory) throws InterruptedException
    {
        File[] files=directory.listFiles();
        for(File file:files)
        {
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }
    public static void search(File file,String keyword) throws IOException
    {
        try(Scanner in=new Scanner(file,"UTF-8"))
        {
            int linenumber=0;
            while(in.hasNextLine()){
                linenumber++;
                String line=in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",file.getPath(),linenumber,line);
                }
            }
        }
    }
}