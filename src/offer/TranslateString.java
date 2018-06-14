package offer;

import java.util.Scanner;

public class TranslateString {
    static public void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        char[] chArr=String.valueOf(n).toCharArray();
        int[] f=new int[chArr.length+1];
        f[chArr.length-1]=1;
        for(int i=chArr.length-2;i>=0;i--){
            f[i]=f[i+1];
            int temp=(chArr[i]-'0')*10+chArr[i+1]-'0';
            if(temp>=0&&temp<=25){
                f[i]+=f[i+2];
            }
        }
        System.out.print(f[0]);

    }
}
