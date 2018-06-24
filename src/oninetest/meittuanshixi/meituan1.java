package oninetest.meittuanshixi;

import java.util.Scanner;

public class meituan1 {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int c=in.nextInt();
        while(c>0){
            int n=in.nextInt();
            int num=getnum(n);
            long ret=0;
            for(int i=0;i<num-1;i++){
                ret+=(i+1)*Math.pow(10,i)*9;
            }
            ret+=num*(n-Math.pow(10,num-1)+1);
            System.out.println(ret);
            c--;
        }

    }
    public static int getnum(int n){
        int ret=0;
        while(n>0){
            ret++;
            n/=10;
        }
        return ret;
    }
}
