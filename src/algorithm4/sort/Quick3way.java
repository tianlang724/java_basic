package algorithm4.sort;

import algorithm4.api.StdRandom;

public class Quick3way extends Sort {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int low,int high){
        if(low>=high) return;
        int lt=low;
        int i=low+1;
        int gt=high;
        Comparable v=a[low];
        while(i<=gt){
            int cmp=a[i].compareTo(v);
            if(cmp<0) exch(a,lt++,i++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }
        sort(a,low,lt-1);
        sort(a,gt+1,high);

    }
    public static void main(String[] arg){
        int N=10;
        Double[] a=new Double[N];
        for(int j=0;j<N;j++){
            a[j]= StdRandom.uniform();
        }
        Quick3way.sort(a);
//        String[] a = StdIn.readAllStrings();
//        Selection.showSort(a);
        show(a);
    }
}
