package algorithm4.sort;

import java.util.Comparator;

abstract class Sort {
    public static void sort(Comparable[] a){}
    public static boolean less(Comparable p,Comparable q){
        return p.compareTo(q)<0;
    }
    public static boolean less(Comparable[] a, int i,int j){
        return a[i].compareTo(a[j])<0;
    }
    public static void exch(Comparable[]a ,int p,int q){
        Comparable t=a[p];
        a[p]=a[q];
        a[q]=t;
    }
    public static void show(Comparable[] a){
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array a[] sorted?
    public static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    public static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }
    public  static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

}
