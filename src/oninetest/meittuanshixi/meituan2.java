package oninetest.meittuanshixi;

import java.util.Scanner;

public class meituan2 {
    static int[] a=new int[100001];
    static int[][] g=new int[100001][100001];
    public static  void main(String[] args){
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();
        int n=in.nextInt();
        int m=in.nextInt();
        int p=in.nextInt();
        geta(N,p);
        long ret=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                ret+=a[gcd(i,j)];
            }
        }
        System.out.println(ret);

    }
    public static int gcd(int m,int n) {
        if (m < n) {
            int t = n;
            n = m;
            m = t;
        }
        int r;
        do {
            r = m % n;
            m = n;
            n = r;
        } while (r != 0);
        return m;
    }
    public static void geta(int N,int p){
        a[1]=p;
        for(int i=2;i<=N;i++){
            a[i]=(a[i-1]+153)%p;
        }
    }
}
