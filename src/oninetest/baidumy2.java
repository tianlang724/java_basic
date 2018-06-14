package oninetest;

import java.util.Arrays;
import java.util.Scanner;

public class baidumy2 {
    static int n;
    static int now;
    static int curnum=0;
    static int minnum=Integer.MAX_VALUE;
    static int[] a=new int[20010];
    static boolean[] vis=new boolean[20010];
    static public void main(String[] args){
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        for(int i=1;i<=n;i++){
            Arrays.fill(vis,false);
            now=i;
            curnum=1;
            if(!vis[i]){
                dfs(a[i]);
            }
        }
        System.out.println(minnum);
    }
    public static void dfs(int x){
        if(x==now){
            if(curnum<minnum)
                minnum=curnum;
        }
        if(curnum>minnum||curnum>n){
            return ;
        }
        if(!vis[x]){
            vis[x]=true;
            curnum++;
            dfs(a[x]);
        }
    }

}
