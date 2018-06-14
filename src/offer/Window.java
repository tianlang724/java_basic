package offer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Window {
    public static void main(String[] args){
        int[] t={2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows(t,3));
    }

    static public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> ret=new ArrayList<>();
        int len=num.length;

        LinkedList<Integer> queue=new LinkedList<>();
        for(int i=0;i<len;i++){
            if(queue.isEmpty()){
                queue.addLast(i);
            }else{
                while(!queue.isEmpty()&&num[i]>num[queue.getLast()]){
                    queue.removeLast();
                }
                queue.addLast(i);
            }
            if(i>=size-1){
                ret.add(num[queue.getFirst()]);
                if(i-size+1==queue.getFirst())
                    queue.removeFirst();
            }

        }
        return ret;
    }
}


