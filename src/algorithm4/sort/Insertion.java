package algorithm4.sort;

import algorithm4.api.StdIn;

public class Insertion extends Sort {

    public static void sort(Comparable[] a){
        int N=a.length;
        for(int i=1;i<N;i++){
            for(int j=i;j>=1&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }
    public static void sort(Comparable[] a,int low,int high){
        for(int i=low;i<=high;i++){
            for(int j=i;j>low&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }
    public static void showSort(Comparable[] a){
        int N=a.length;
        for(int i=1;i<N;i++){
            for(int j=i;j>=1&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
            show(a);
        }
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.showSort(a);
        show(a);
    }
}
