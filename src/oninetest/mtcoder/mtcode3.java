package oninetest.mtcoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class mtcode3 {
    static public void main(String[] args){
        double[][] match=new double[16][16];
//        try{
//            BufferedReader br = new BufferedReader(new FileReader("input.txt"));//构造一个BufferedReader类来读取文件
//            String s = null;
//            int i=0;
//            int j=0;
//            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//                String[] sarr=s.split(" ");
//                for(j=0;j<sarr.length;j++){
//                    match[i][j]=Double.parseDouble(sarr[j]);
//                }
//                i++;
//
//            }
//            br.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        Scanner in=new Scanner(System.in);
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                match[i][j]=in.nextDouble();
            }
        }
        double[] eight=new double[16];
        for(int i=0;i<16;i++){
            int j=0;
            if(i%2==0){
                j=i+1;
            }else{
                j=i-1;
            }
            eight[i]=match[i][j];
        }
        double[] four=new double[16];
        for(int i=0;i<16;i++){
            int j=0;
            if(i%4<2){
                j=(i/4)*4+2;
            }else{
                j=(i/4)*4;
            }
            four[i]=eight[i]*(eight[j]*match[i][j]+eight[j+1]*match[i][j+1]);
        }
        double[] two=new double[16];
        for(int i=0;i<16;i++){
            int j=0;
            int temp=i/4;
            if(temp%2==0){
                j=temp*4+4;
            }else{
                j=temp*4-4;
            }
            two[i]=four[i]*(four[j]*match[i][j]+four[j+1]*match[i][j+1]+four[j+2]*match[i][j+2]+four[j+3]*match[i][j+3]);
        }
        double[] one=new double[16];
        for(int i=0;i<16;i++){
            int j=0;
            if(i/8==0){
                j=8;
            }else{
                j=0;
            }
            double sum=0;
            for(int t=0;t<8;t++){
                sum+=two[j+t]*match[i][j+t];
            }
            one[i]=two[i]*sum;
        }
        for(int i=0;i<16;i++){
            if(i!=0)
                System.out.print(" ");
            System.out.printf("%.10f",one[i]);
        }
    }
}
