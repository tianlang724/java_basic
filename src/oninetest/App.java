package oninetest;

import algorithm4.api.In;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    static public void main(String[] args){
        Long();
    }
    static int[] visited=new int[200];
    static int applen;
    static int[] app=new int[200];
    static int[] value=new int[200];
    static int myapp;
    static int maxvalue=0;
    static int[] target;
    static public void Long(){
        Scanner in = new Scanner(System.in);
        myapp=Integer.parseInt(in.nextLine());
        String line1=in.nextLine();
        String[] appstring=line1.split(" ");
        int len=appstring.length;
        applen=len;
        //int[] app=new int[len];
        for(int i=0;i<len;i++){
            app[i]= Integer.parseInt(appstring[i]);
        }
        //int[] value=new int[len];
        for(int i=0;i<len;i++){
            value[i]=in.nextInt();
        }
        visit(0,0,0);
        int targetnum=0;
        for(int i=0;i<len;i++){
            if(target[i]!=0)
                targetnum++;
        }
        for(int i=0;i<len;i++){
            if(target[i]==1){
                System.out.print(i+1);
                if(targetnum>1){
                    System.out.print("*");
                    targetnum--;
                }
            }
        }
    }
    public static void visit(int i,int curvalue,int curapp){
        if(i>=applen||curapp>=myapp){
            if(curvalue>maxvalue){
                maxvalue=curvalue;
                target=Arrays.copyOf(visited,i);
            }
            return ;
        }
        if(curapp+app[i]<=myapp){
            visited[i]=1;
            visit(i+1,curvalue+value[i],curapp+app[i]);
            visited[i]=0;
            visit(i+1,curvalue,curapp);

        }else{
            visited[i]=0;
            visit(i+1,curvalue,curapp);

        }



    }
}
