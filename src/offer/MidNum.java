package offer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MidNum {
    PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
    PriorityQueue<Integer> minHeap=new PriorityQueue<>();
    int size=0;
    public void Insert(Integer num) {
        size++;
        if(size%2==1){
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        }else{
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        }
    }

    public Double GetMedian() {
        if(size%2==1){
            return (double)maxHeap.peek();
        }else{
            return ((double)(maxHeap.peek()+minHeap.peek()))/2;
        }

    }
}
