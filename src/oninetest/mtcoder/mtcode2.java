package oninetest.mtcoder;

import java.util.Scanner;

public class mtcode2
{
    static public void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        double rate=(double)m/n;
        int sat1=0;
        int sat2=0;
        double maxsat=Integer.MIN_VALUE;
        int choice=-1;
        for(int i=0;i<k;i++){
            sat1=in.nextInt();
            sat2=in.nextInt();
            double temp=sat1*rate+sat2*(1-rate);
            if(temp>maxsat){
                maxsat=temp;
                choice=i;
            }
        }
        for(int i=0;i<k;i++){
            if(i==choice) {
                System.out.print(n+" ");
            }else if(i==k-1){
                System.out.print(0);
            }else{
                System.out.print(0+" ");
            }
        }
    }
}
