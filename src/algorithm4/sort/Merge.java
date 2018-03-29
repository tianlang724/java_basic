package algorithm4.sort;

import algorithm4.api.StdIn;

public class Merge extends Sort{
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        if(lo>=hi) return;
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    private static void merge(Comparable[] a,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        for(int k=low;k<=high;k++)
            aux[k]=a[k];
        for(int k=low;k<=high;k++){
            if(i>mid) a[k]=aux[j++];
            else if(j>high) a[k]=aux[i++];
            else if(less(aux[j],aux[i])) a[k]=aux[j++];
            else a[k]=aux[i++];
        }
    }
    public static void showSort(Comparable[] a){
        aux=new Comparable[a.length];
        showSort(a,0,a.length-1);
    }
    private static void showSort(Comparable[] a,int lo,int hi){
        if(lo>=hi) return;
        int mid=lo+(hi-lo)/2;
        showSort(a,lo,mid);
        showSort(a,mid+1,hi);
        merge(a,lo,mid,hi);
        show(a);

    }
    public static void main(String[] arg){
//        int N=10;
//        Double[] a=new Double[N];
//        for(int j=0;j<N;j++){
//            a[j]= StdRandom.uniform();
//        }
//        Merge.sort(a);
        String[] a = StdIn.readAllStrings();
        Selection.showSort(a);
        show(a);
    }
}
