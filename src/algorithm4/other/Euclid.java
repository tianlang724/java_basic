package algorithm4.other;

import algorithm4.api.StdOut;

public class Euclid {
    public void Euclid() {}
    public static int EuclidRecursion(int a,int b){
        if(a==0||b==0) return 0;
        if(b>a){
            int temp=a;
            b=a;
            a=temp;
        }
        if(a%b==0) return b;
        else return EuclidRecursion(b,a%b);
    }
    public static int EuclidCirculation(int a,int b){
        while((a%b)>0){
            int temp=a;
            a=b;
            b=temp%b;
        }
        return b;
    }
    public static void main(String[] arg){
        StdOut.println(EuclidRecursion(1234567,1111111));
        //StdOut.println(EuclidCirculation(1234567,1111111));
    }
}
