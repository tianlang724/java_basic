package algorithm4.find;
import algorithm4.api.In;
import algorithm4.api.StdIn;
import algorithm4.api.StdOut;

import java.util.Arrays;

public class BinarySearch {
    public static int search(int key,int[] a){
        int lo=0;
        int hi=a.length-1;
        // 需要等于
        while(lo<=hi){
            //不可以用(lo+hi)/2,System.out.print((0+15)/2))为7,System.out.print((double)(0+15)/2)为7.5
            int mid=lo+(hi-lo)/2;
            if(a[mid]>key) hi=mid-1;
            else if(a[mid]<key) lo=mid+1;
            else return mid;
        }
        return -1;
    }
    public static int rank(int key,int[] a){
        int ret=search(key,a);
        if (ret==-1) return 0;
        while(a[ret]==key)
            ret--;
        ret++;
        return ret;
    }
    public static int count(int key,int[] a){
        int i=search(key,a);
        if(i==-1) return 0;
        int j=i;
        int num=0;
        while(j<a.length&&a[j++]==key)
            num++;
        j=i-1;
        while(j>=0&&a[j--]==key)
            num++;
        return num;

    }
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        // read the integers from a file
        //In in = new In(args[0]);
        In in=new In(System.getProperty("user.dir")+"\\src\\main\\resources\\arr.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.search(key,whitelist) == -1)
                StdOut.println(key);
            else StdOut.println("ok");
        }

        System.out.println(BinarySearch.rank(5,whitelist));
        System.out.println(BinarySearch.count(5,whitelist));

    }
}
