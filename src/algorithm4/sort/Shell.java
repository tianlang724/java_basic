package algorithm4.sort;

import algorithm4.api.StdIn;
import algorithm4.api.StdRandom;

public class Shell  extends Sort{
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while(h<N/3) h=3*h+1;
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }
    public static void showSort(Comparable[] a){
        int N=a.length;
        int h=1;
        while(h<N/3) h=3*h+1;
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
            show(a);
        }
    }
    public static void main(String[] args) {
//        int N=10;
//        Double[] a=new Double[N];
//        for(int j=0;j<N;j++){
//            a[j]= StdRandom.uniform();
//        }
//        Selection.sort(a);
//        show(a);
        String[] a = StdIn.readAllStrings();
        Shell.showSort(a);
        show(a);
    }
}
