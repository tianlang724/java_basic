package core.reflection;
import java.lang.reflect.*;
import java.util.*;

public class CopyOfTest{
    public static void main(String[] args){
        int a[]={1,2,3};
        a=(int[])goodCopyof(a,10);
        System.out.println(Arrays.toString(a));
        String b[]={"aa","bb","cc"};
        b=(String [])goodCopyof(b,10);
        System.out.println(Arrays.toString(b));
        System.out.println("the following call will generation an exception");
        b=(String[])badCopyOf(b,10);
    }
    public static Object badCopyOf(Object[] a,int newLength){
        Object[] newArray=new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }
    public static Object goodCopyof(Object a,int newLength){
        Class cl=a.getClass();
        if(!cl.isArray()) return null;
        Class componentType=cl.getComponentType();
        int len=Array.getLength(a);
        Object newArray=Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(len,newLength));
        return newArray;
    }
}