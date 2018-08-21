package test;

import thinking.net.mindview.util.RandomGenerator;

import java.util.Random;

public class duoyi {
    static int n=4;
    static public void main(String[] args){
        int[] weight={10,20,30,40};
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=weight[i];
        }
        int[] rate=new int[n];
        rate[0]=100*weight[0]/sum;
        for(int i=1;i<n;i++){
            rate[i]=rate[i-1]+100*weight[i]/sum;
        }
        int[] num=new int[n];
        for(int j=0;j<100;j++){
            Random random=new Random();
            int t=random.nextInt(100);
            for(int i=0;i<n;i++){
                if(i==0&&t<=rate[0]){
                    System.out.println(0);
                    num[0]++;
                    break;
                }else if(t<=rate[i]&&t>rate[i-1]){
                    System.out.println(i);
                    num[i]++;
                    break;
                }
            }
        }
        System.out.println("共取物品100次，结果如下：");
        for(int i=0;i<n;i++){
            System.out.println("取 "+i+" 物品"+num[i]+"次");
        }
    }

}
