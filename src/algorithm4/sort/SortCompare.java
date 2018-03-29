package algorithm4.sort;

import algorithm4.api.StdRandom;
import algorithm4.api.Stopwatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SortCompare {
    public static double time(String alg,Double[] a){
//        try {
//            Class algClass=Class.forName(alg);
//            printMethods(algClass);
//            Method method=algClass.getMethod("test");
//            Stopwatch watch=new Stopwatch();
//            method.invoke(a);
//            double time=watch.elapsedTime();
//            return time;
//            } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return 0.0;
        Stopwatch timer=new Stopwatch();
        if(alg.equals("MergeBU")) MergeBU.sort(a);
        if(alg.equals("Merge")) Merge.sort(a);
        if(alg.equals("MergeX")) MergeX.sort(a);

        if(alg.equals("Insertion")) Insertion.sort(a);
        if(alg.equals("Selection")) Selection.sort(a);
        if(alg.equals("Shell")) Shell.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String arg,int N,int T){
        double total=0.0;
        Double[] a=new Double[N];
        for(int i=0;i<T;i++){
            for(int j=0;j<N;j++){
                a[j]= StdRandom.uniform();
            }
            total+=time(arg,a);
        }
        return total;
    }
    public static void main(String[] arg){
        String arg1="Merge";
        String arg2="MergeX";
        String arg3="MergeBU";
        int N=1000000;
        int T=10;
        double t1=timeRandomInput(arg1,N,T);
        double t2=timeRandomInput(arg2,N,T);
        double t3=timeRandomInput(arg3,N,T);
        System.out.println(arg1+"="+t1+","+arg2+"="+t2+","+arg3+"="+t3);
    }
}
