package offer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class yuesefu {
    public static  void main(String[] args){
        //System.out.println(LastRemaining_Solution(5,3));
        //LinkedList
    }
    public static int LastRemaining_Solution(int n, int m) {
        if(n==0||m==0)
            return -1;
        if(n==1)
            return 0;
        LinkedList<Integer> stu=new LinkedList<>();
        for(int i=0;i<n;i++){
            stu.add(i);
        }
        int start=0;
        while(stu.size()>1){
            start=(start+m-1)%stu.size();
            stu.remove(start);
        }
        return stu.getFirst();

    }
}
