package oninetest;

import java.util.Arrays;
import java.util.Scanner;

public class ali1 {
    static int startx;
    static int starty;
    static int starto;
    static int endx;
    static int endy;
    static int endo;
    static int rows;
    static int cols;
    static int[][] map;
    static int[][][] vis;
    static int ret=65535;
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);

        startx=in.nextInt();
        starty=in.nextInt();
        String s=in.nextLine();
        starto=getO(s.trim());
        endx=in.nextInt();
        endy=in.nextInt();
        s=in.nextLine();
        endo=getO(s.trim());
        rows=in.nextInt();
        cols=in.nextInt();
        map=new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                map[i][j]=in.nextInt();
            }
        }
        vis=new int[rows][cols][4];
        //vis[startx][starty][starto]=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                Arrays.fill(vis[i][j],65535);
            }
        }
        check(startx,starty,starto,0);
        System.out.println(ret);

    }
    public static void check(int x,int y,int o,int cur){
        if(x==endx&&y==endy){
            cur+=(Math.abs(o-endo)==2?2:1);
            if(cur<ret)
                ret=cur;
        }
        if(cur>=vis[x][y][o]||map[x][y]==1)
            return ;
        vis[x][y][o]=cur;
        if(o==0&&y+1<cols){
            check(x,y+1,o,cur+1);
        }else if(o==1&&x+1<rows){
            check(x+1,y,o,cur+1);
        }else if(o==2&&y-1>=0){
            check(x,y-1,o,cur+1);
        }else if(o==3&&x-1>=0){
            check(x-1,y,o,cur+1);
        }
        for(int i=3;i>=0;i--){
            int step=Math.abs(i-o)==2?2:1;
            if(o!=i){
                check(x,y,i,cur+step);
            }
        }
    }
    public static int getO(String s){
        if(s.equals("EAST"))
            return 0;
        if(s.equals("SOUTH"))
            return 1;
        if(s.equals("WEST"))
            return 2;
        if(s.equals("NORTH"));
            return 3;
    }
}
