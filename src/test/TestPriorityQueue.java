package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestPriorityQueue {
    static public void main(String[] strings){
        Queue<Integer> queue=new PriorityQueue<>(new IntergerComparator());
        for(int i=0;i<5;i++){
            queue.add(i);
        }
        for(int i=0;i<5;i++){
           System.out.print(queue.poll());
        }
        System.out.println();
        queue.clear();
        for(int i=5;i>=0;i--){
            queue.add(i);
        }
        for(int i=0;i<5;i++){
            System.out.print(queue.poll());
        }
        System.out.println();
    }
}
class IntergerComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Integer i1=(Integer)o1;
        Integer i2=(Integer)o2;
        if(i1<i2)
            return 1;
        if(i1>i2)
            return -1;
        return 0;
    }
}
