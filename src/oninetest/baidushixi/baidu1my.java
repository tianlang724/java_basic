package oninetest.baidushixi;

import java.util.Scanner;

public class baidu1my {
    public static  void main(String[] args){
        long[][] f=new long[20][20];
        int[] a=new int[20];
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        in.nextLine();
        String ss=in.nextLine();
        for(int i=1;i<=ss.length();i++){
            a[i]=ss.charAt(i-1)-'0';
        }
        long temp=0;
        for(int i=1;i<=n;i++){
            temp=temp*10+a[i];
            f[i][0]=temp;
        }
        for(int i=1;i<=m;i++){
            for(int j=i+1;j<=n;j++){
                for(int k=i;k<j;k++){
                    temp=0;
                    for(int v=k+1;v<=j;v++){
                        temp=temp*10+a[v];
                    }
                    if(f[k][i-1]*temp>f[j][i])
                        f[j][i]=f[k][i-1]*temp;
                }
            }
        }
        System.out.println(f[n][m]);
    }


}
