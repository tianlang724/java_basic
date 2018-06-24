package oninetest.mtcoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MTcode1{
    static ArrayList<Point> nodes=new ArrayList<>();
    static int[][] edge;
    static int n;
    static int m;
    static int k;
    static int[][] lieqiang;
    static int[][] hangqiang;
    static int[][] dist;
    static int[][] map;
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        m=in.nextInt();
        k=in.nextInt();
        dist=new int[n][m];
        map=new int[k+1][k+1];
        lieqiang=new int[n][m];
        in.nextLine();
        for(int i=0;i<n;i++){
            String s=in.nextLine();
            char[] ch=s.toCharArray();
            for(int j=0;j<m-1;j++){
                lieqiang[i][j]=ch[j]-'0';
            }
        }
        hangqiang=new int[n][m];
        for(int i=0;i<n-1;i++){
            String s=in.nextLine();
            char[] ch=s.toCharArray();
            for(int j=0;j<m;j++){
                hangqiang[i][j]=ch[j]-'0';
            }
        }

        for(int i=0;i<k;i++){
            nodes.add(new Point(in.nextInt(),in.nextInt()));
        }
        for(int i=0;i<k-1;i++){
            dfs(i);
        }
        System.out.println(prim(map,k));

    }
    public static void dfs(int x){
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        Point point= nodes.get(x);
        dsfhelp(point.x-1,point.y-1,0);
        for(int i=x+1;i<k;i++){
            Point temp=nodes.get(i);
            map[x][i]=dist[temp.x-1][temp.y-1];

        }

    }
    public static void dsfhelp(int x,int y,int d){
        if(d>=dist[x][y]){
            return;
        }
        if(x>=n||y>=m||x<0||y<0)
            return;
        dist[x][y]=d;
        if(y+1<m&&lieqiang[x][y]==0){
            dsfhelp(x,y+1,d+1);
        }
        if(y-1>=0&&lieqiang[x][y-1]==0){
            dsfhelp(x,y-1,d+1);
        }
        if(x+1<n&&hangqiang[x][y]==0){
            dsfhelp(x+1,y,d+1);
        }
        if(x-1>=0&&hangqiang[x-1][y]==0){
            dsfhelp(x-1,y,d+1);
        }

    }

    static public int primeValue(int[][] a, int n) {
        int sum = 0;
        int[] lowcost = new int[n + 1];//边的最小值
        int[] closest = new int[n + 1];//存储最近节点
        boolean[] visit = new boolean[n + 1];
        visit[1] = true;//visit为true则说明该节点已经被访问
        for(int i = 2; i <= n; i++) {
            lowcost[i] = a[1][i];
            closest[i] = 1;
            visit[i] = false;
        }
        for(int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            for (int k = 2; k <= n; k++)
                if ((lowcost[k] < min) && (!visit[k])) {
                    min = lowcost[k];
                    j = k;
                }
            sum += min;//累加最小生成树的边权值s
            visit[j] = true;
            for(int k = 2; k <= n; k++) {
                if (a[j][k] < lowcost[k] && !visit[k]) {
                    lowcost[k] = a[j][k];
                    closest[k] = j;
                }
            }
        }
        return sum;
    }
    public static int prim(int[][] graph, int n){

        int[] lowcost = new int[n];
        int[] mst = new int[n];
        int i, j, min, minid, sum = 0;

        for(i = 1; i < n; i++){
            lowcost[i] = graph[0][i];
            mst[i] = 0;
        }

        for(i = 1; i < n; i++){

            min = Integer.MAX_VALUE;
            minid = 0;
            for(j = 1; j < n; j++){
                if (lowcost[j] < min && lowcost[j] != 0) {
                    min = lowcost[j];
                    minid = j;
                }
            }

            sum += min;
            lowcost[minid] = 0;

            for (j = 1; j < n; j++) {
                if (graph[minid][j] < lowcost[j]) {
                    lowcost[j] = graph[minid][j];
                    mst[j] = minid;
                }
            }
        }

        return sum;

    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}