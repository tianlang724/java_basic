package core.set.priorityqueue;
import java.util.*;
import java.time.*;
public class PriorityQueueTest{
    public static void main(String [] args){
        PriorityQueue<LocalDate> pq=new PriorityQueue<>();
        pq.add(LocalDate.of(1993,07,24));
        pq.add(LocalDate.of(1993,4,26));
        pq.add(LocalDate.of(1903,9,29));
        pq.add(LocalDate.of(1973,2,28));
        System.out.println("Iteraing over element...");
        for(LocalDate date:pq)
            System.out.println(date);
        System.out.println("Removing elments...");
        while(!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}