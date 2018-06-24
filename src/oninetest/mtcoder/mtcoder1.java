package oninetest.mtcoder;

import java.util.Scanner;

public class mtcoder1 {
    static public void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int ai=0;
        int flag=0;
        double sum=0;
        double tejiayouhui=0;
        for(int i=0;i<n;i++){
            ai=in.nextInt();
            flag=in.nextInt();
            sum+=ai;
            tejiayouhui=tejiayouhui+ai*flag*0.2;
        }
        double manjian=0;
        int bi=0;
        int ci=0;
        for(int i=0;i<m;i++){
            bi=in.nextInt();
            ci=in.nextInt();
            if(bi<=sum&&ci>manjian){
                manjian=ci;
            }
        }
        if(manjian>tejiayouhui) {
            System.out.printf("%.2f", sum - manjian);
        }else{
            System.out.printf("%.2f", sum - tejiayouhui);
        }
    }
}
