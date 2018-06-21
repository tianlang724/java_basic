package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class SubArrayNumber {
    static  public  void main(String[] args){
        int[] arr={3,2,1};
        System.out.println(getSubArray(arr,1));
    }
    static public int getSubArray(int[] arr,int num){
        if(arr.length<=0){
            return 0;
        }
        int res=0;
        LinkedList<Integer>  maxQueue=new LinkedList<>();
        LinkedList<Integer>  minQueue=new LinkedList<>();
        int len=arr.length;
        int i=0,j=0;
        while(i<len){

            while (j<len){
                while (!maxQueue.isEmpty()&&arr[maxQueue.getLast()]<=arr[j]){
                    maxQueue.removeLast();
                }
                maxQueue.addLast(j);
                while (!minQueue.isEmpty()&&arr[minQueue.getLast()]>=arr[j]){
                    minQueue.removeLast();
                }
                minQueue.addLast(j);
                if(arr[maxQueue.getFirst()]-arr[minQueue.getFirst()]>num){
                    break;
                }
                j++;

            }
            if(!minQueue.isEmpty()&&minQueue.getFirst()==i){
                minQueue.removeFirst();
            }
            if(!maxQueue.isEmpty()&&maxQueue.getFirst()==i){
                maxQueue.removeFirst();
            }
            res+=j-i;
            i++;


        }
        return res;

    }
}
