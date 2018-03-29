package concurrency_art;

import java.lang.management.*;



public class MultiThread{
    public static void main(String[] args){
        ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos=threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo info:threadInfos){
            System.out.println("["+info.getThreadId()+"]"+info.getThreadName());
        }
    }
}