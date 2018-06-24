package oninetest;

import java.util.ArrayList;
import java.util.Scanner;

public class Tplingk {
    public static void main(String[] args){
      FindContinuousSequence(9);
       FindContinuousSequence(16);

    }
    static public void FindContinuousSequence(int sum) {
        if(sum<=1){
            System.out.println(sum);
            return;
        }
        int small=1;
        int big=2;
        boolean flag=false;
        while(small!=(1+sum)/2){          //当small==(1+sum)/2的时候停止
            int curSum=sumOfList(small,big);
            if(curSum==sum){
                flag=true;
                ArrayList<Integer> list=new ArrayList<Integer>();
                for(int i=small;i<=big;i++){
                    list.add(i);
                }
                for(int i=0;i<list.size();i++){
                    System.out.print(list.get(i)+" ");
                }
                System.out.println();
                small++;big++;
            }else if(curSum<sum){
                big++;
            }else{
                small++;
            }
        }
        if(!flag){
            System.out.println("不存在");
        }

    }

    static int sumOfList(int head,int leap){        //计算当前序列的和
        int sum=head;
        for(int i=head+1;i<=leap;i++){
            sum+=i;
        }
        return sum;
    }

}
