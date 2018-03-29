package core.generics.pair2;

import core.generics.pair1.Pair;
import java.time.*;

public class PairTest2
{
    public static void main(String[] args)
    {
        LocalDate[] birth={
            LocalDate.of(1999,10,23),
            LocalDate.of(1993,7,24),
            LocalDate.of(2014,10,10),
            LocalDate.of(2017,10,1),
        };
        Pair<LocalDate> mm=ArrayAlg.minmax(birth);
        System.out.println("min="+mm.getFirst());
        System.out.println("max="+mm.getSecond());
    }
}
class ArrayAlg{
    public static <T extends Comparable> Pair<T> minmax(T[] a){
        if(a==null||a.length==0) return null;
        T min=a[0];
        T max=a[0];
        for(int i=1;i<a.length;i++)
        {
            if(min.compareTo(a[i])>0) min=a[i];
            if(max.compareTo(a[i])<0) max=a[i];
        }
        return new Pair<>(min,max);
    }
}