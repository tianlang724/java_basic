package offer;

import java.util.ArrayList;
import java.util.Arrays;

public class minK {
    static public void main(String[] args){
        int[] test={4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution(test,4));
    }
    static  public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> ret=new ArrayList<>();
        if(input.length<k){
            return ret;
        }
        int index=quickSort(input,k,0,input.length-1);
        while(index!=k-1){
            if(index>k-1){
                index=quickSort(input,k,0,index-1);
            }else{
                index=quickSort(input,k,index+1,input.length-1);
            }
        }
        Arrays.sort(input,0,k);
        for(int i=0;i<k;i++){
            ret.add(input[i]);
        }
        return ret;
    }
    static  public int quickSort(int [] input, int k,int low,int high){
        int base=input[low];
        int i=low+1;
        int j=high;
        while(i<j){
            while(j>i&&input[j]>base) j--;
            while(i<j&&input[i]<base) i++;
            if(i>=j)
                break;
            int temp=input[i];
            input[i]=input[j];
            input[j]=temp;
        }
        input[low]=input[j];
        input[j]=base;
        return j;
    }
}
