package oninetest;

import java.util.Arrays;
import java.util.Scanner;

public class baidu {
    static int n;
    static int[] a=new int[200010];
    static int[] v=new int[200010];
    static int[] u=new int[200010];
    static int maxx;
    static int now;
    public static void main(String[] args){
        maxx=Integer.MAX_VALUE;
        Arrays.fill(a,0);
        Arrays.fill(v,0);
        Arrays.fill(u,0);
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        for(int i=1;i<=n;i++)
        {
            now=i;
            if(v[i]==0)
            {
                u[i]=now;
                v[i]=1;
                dfs(a[i],2);
            }
        }
        System.out.println(maxx);
    }
    public static void dfs(int x,int z)
    {
        if(v[x]!=0)
        {
            if(u[x]==now)
                maxx=Math.min(maxx,z-v[x]);
            return ;
        }
        u[x]=now;
        v[x]=z;
        dfs(a[x],z+1);
        return ;
    }

}
