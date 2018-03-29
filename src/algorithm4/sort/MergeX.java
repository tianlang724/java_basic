package algorithm4.sort;

import algorithm4.api.StdIn;
import algorithm4.api.StdRandom;

public class MergeX extends Sort{
    private static final int CUTOFF = 7;  // cutoff to insertion sort

    private MergeX() { }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];   // to ensure stability
            else                           dst[k] = src[i++];
        }
       assert isSorted(dst, lo, hi);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {

        if (hi <= lo + CUTOFF) {
            Insertion.sort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
//        if (!less(src[mid+1], src[mid])) {
//            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
//            return;
//        }

        merge(src, dst, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);
        assert isSorted(a);
    }


    public static void main(String[] arg){
        int N=10;
        Double[] a=new Double[N];
        for(int j=0;j<N;j++){
            a[j]= StdRandom.uniform();
        }
        MergeX.sort(a);
//        String[] a = StdIn.readAllStrings();
//        Selection.showSort(a);
        show(a);
    }
}
