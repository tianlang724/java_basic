package offer;

import java.util.ArrayList;
import java.util.List;

public class Duplicate {
    public static List<Integer> duplicate(int a[]){
        List<Integer> ret=new ArrayList<Integer>();
        int len=a.length;
        for(int i=0;i<len;){
            if(a[i]!=i){
                if(a[a[i]]==a[i]){
                    ret.add(a[i]);
                    i++;
                }else{
                    int temp=a[i];
                    a[i]=a[a[i]];
                    a[temp]=temp;
                }
            }else{
                i++;
            }
        }
        return ret;
    }
    public static void main(String[] args){
        int[] test={3,1,2,0,2,5,3};
        System.out.println(duplicate(test));
    }
}
