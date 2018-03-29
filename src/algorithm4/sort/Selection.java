package algorithm4.sort;

import algorithm4.api.StdIn;

public class Selection extends Sort {
    public static void sort(Comparable[] a) {
        int N=a.length;
        for(int i=0;i<N;i++){
            int min=i;
            for(int j=i+1;j<N;j++){
                if(less(a[j],a[min])) min=j;
            }
            exch(a,min,i);
        }
    }
    public static void showSort(Comparable[] a) {
        int N=a.length;
        for(int i=0;i<N;i++){
            int min=i;
            for(int j=i+1;j<N;j++){
                if(less(a[j],a[min])) min=j;
            }
            exch(a,min,i);
            show(a);
        }
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.showSort(a);
        show(a);
    }
}
