package algorithm4.sort;

import algorithm4.api.StdIn;
import algorithm4.api.StdRandom;

public class Heap {
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
    public static void sort(Comparable[] a){
        int N=a.length;
        // 构造堆,每个元素本来就是一个堆，所以直接从右至左的一半开始构造
        for(int k=N/2;k>=1;k--){
            sink(a,k,N);
        }
        //将构造好的堆转换成排序数组，不断的将最大的元素出堆，并放在数组的最后，使其无效化
        while(N>0){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }
    private static void sink(Comparable[] a,int k,int n){
        while(2*k<n){
            int j=2*k;
            if(j<n&&less(a,j,j+1)) j++;
            if(!less(a,k,j)) break;
            exch(a,k,j);
            k=j;
        }
    }
    public static void show(Comparable[] a){
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }
    public static void main(String[] arg){
        int N=10;
        Integer[] t={1,8,6,2,5,4,7,3};
        Heap.sort(t);
//        String[] a = StdIn.readAllStrings();
//        Selection.showSort(a);
        show(t);
    }
}
