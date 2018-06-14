package oninetest;

import java.util.Scanner;

public class toutiao2 {
    public static  void main(String[]args){
        Scanner in=new Scanner(System.in);
        int m=in.nextInt();
        int n=in.nextInt();
        in.nextLine();
        String[] strings=new String[m];
        for(int i=0;i<m;i++){
            strings[i]=in.nextLine();
        }
        String temp;
        in.nextLine();
        for(int i=0;i<n;i++){
            temp=in.nextLine();
            char[] tpch=temp.toCharArray();
            int j=0;
            for(j=0;j<strings.length;j++){
                int k=0;
               while(k<strings[j].length()&&k<tpch.length){
                    if(tpch[k]==strings[j].charAt(k)){
                        k++;
                    }else{
                        break;
                    }
                }
                if(k==strings[j].length()){
                   System.out.println(1);
                   break;
                }
            }
            if(j==strings.length){
                System.out.println(-1);
            }
        }
    }
}
