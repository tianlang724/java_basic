package oninetest.zhaoyin;

import java.util.Scanner;

public class zhaoyin1 {

    public static int chargeTypes(int[] coinsValues, int n){
        int m = coins;
        int[][] c = new int[m+1][n+1];

        //基准条件,可参考下面的递归代码
        for(int i = 0; i <=m; i++)
            c[i][0] = 1;
        for(int i = 1; i <=n; i++)
            c[0][i] = 0;


        for(int i = 1; i <=m; i++)
        {
            for(int j = 1; j <=n; j++)
            {
                if(j < coinsValues[i-1])//第 i 枚硬币 不可用. (需要找 5块钱,但是现在只有一张百元大钞)
                {
                    c[i][j] = c[i-1][j];
                    continue;
                }
                //在第 i 枚硬币可用的情况下, 不使用 第 i 枚硬币 或者第 i 枚硬币至少使用一次---状态方程
                c[i][j] = c[i-1][j] + c[i][j - coinsValues[i-1]];//coinsValues下标从0开始
            }
        }
        return c[m][n];
    }

    //递归实现
    public static int recursiveChargeTypes(int[] coinsValues, int m, int n)
    {
        //基准条件 可以 通过画一个简单的实例 分析来得出. 比如 recursiveChargeTypes({1,3,4}, 3, 5)
        if(n == 0)
            return 1;
        if(n < 0)
            return 0;
        if(m <= 0)
            return 0;
        else
            return recursiveChargeTypes(coinsValues, m-1, n) + recursiveChargeTypes(coinsValues, m, n-coinsValues[m]);
    }
    static int coins=0;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] coinvalue=new int[201];
        int n=in.nextInt();
        while(n>0){
            coins=in.nextInt();
            int value=in.nextInt();
            for(int i=0;i<coins;i++){
                coinvalue[i]=in.nextInt();
            }
            System.out.println( chargeTypes(coinvalue, value));
            n--;
        }
    }
}
