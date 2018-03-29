package algorithm4.sort;

import algorithm4.api.StdRandom;

public class Quick extends  Sort {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){

        if(hi<=lo) return;
        int j=parttion(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    private static int parttion(Comparable[] a,int lo,int hi){
        int i=lo+1;
        int j=hi;
        Comparable v=a[lo];
        while(i<=j){
            while(i<=hi&&less(a[i],v)) i++;
            while(j>=0&&less(v,a[j])) j--;

            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;

    }
    public static void main(String[] arg){
        int N=15;
        Integer[] a=new Integer[N];
        for(int j=0;j<N;j++){
            a[j]= StdRandom.uniform(100);
        }
        Integer[] b={7,1,4,9,11,12,10};
        Quick.sort(b);
        //String[] a = StdIn.readAllStrings();
        Selection.showSort(b);
        //show(a);
    }
}
