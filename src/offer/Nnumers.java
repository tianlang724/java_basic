package offer;

import java.util.Scanner;

public class Nnumers {
    static public  void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] number=new int[n];
        boolean flag=false;
        while(true){
            number[0]++;
            int carry=number[0]/10;
            number[0]=number[0]%10;
            int i=1;
            while(carry>0){
                if(i==n){
                    flag=true;
                    break;
                }
                number[i]++;
                carry=number[i]/10;
                number[i]=number[i]%10;
                i++;
            }
            if(flag)
                break;
            boolean nofirst=false;
            for(i=n-1;i>=0;i--){
                if(number[i]>0||nofirst){
                    System.out.print(number[i]);
                    nofirst=true;
                }
            }
            System.out.println();
        }
    }
}
