package oninetest.zhaoyin;



import java.util.Scanner;

public class zhaoyin2 {
    public static  void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int nhouse=0;
        int[] arr=new int[1001];
        int[][] dp=new int[1001][1001];
        while(n>0){
            nhouse=in.nextInt();
            for(int i=1;i<=nhouse;i++){
                arr[i]=in.nextInt();
            }
            dp[1][0]=0;
            dp[1][1]=arr[1];
            for(int i=2;i<=nhouse;i++){
                if(i<nhouse){
                    dp[i][0]=Math.max(dp[i-1][1],dp[i-1][0]);
                    dp[i][1]=dp[i-1][0]+arr[i];
            }
            System.out.println(Math.max(dp[nhouse][1],dp[nhouse][0]));
            n--;
        }
    }
}}
