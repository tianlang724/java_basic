package algorithm4.sort;

import algorithm4.api.StdIn;

public class MergeBU extends Sort {
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        int N=a.length;
        aux= new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz){
            for(int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
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
        int N=a.length;
        aux= new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz){
            for(int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
                show(a);
            }
        }
    }
    public static void main(String[] arg){
//        int N=10;
//        Double[] a=new Double[N];
//        for(int j=0;j<N;j++){
//            a[j]= StdRandom.uniform();
//        }
//        MergeBU.showSort(a);
//        show(a);
        String[] a = StdIn.readAllStrings();
        MergeBU.showSort(a);
        show(a);
    }
}
