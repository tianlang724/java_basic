package core.generics.pair3;

import core.equals.Manager;
import core.equals.Employee;
import core.generics.pair1.Pair;
public class PairTest3
{
    public static void main(String[] args)
    {
        Manager ceo=new Manager("ceo",8000,2000,10,10);
        Manager cfo=new Manager("cfo",5000,2001,12,1);
        Pair<Manager> buddies=new Pair<>(ceo,cfo);
        printBuddies(buddies);
        
        ceo.setBonus(10000);
        cfo.setBonus(9000);
        Manager[] managers={ceo,cfo};
        
        Pair<Employee> result=new Pair<>();
        minmaxBonus(managers,result);
        System.out.println("first:"+result.getFirst().getName()+",Second:"+result.getSecond().getName());
        maxminBonus(managers,result);
        System.out.println("first:"+result.getFirst().getName()+",Second:"+result.getSecond().getName());
    }
    public static void printBuddies(Pair<? extends Employee> buddies)
    {
        Employee first=buddies.getFirst();
        Employee second=buddies.getSecond();
        System.out.println(first.getName()+" and "+second.getName()+" are buddies");
    }
    public static void minmaxBonus(Manager[] a,Pair<? super Manager> result)
    {
        if(a.length==0) return ;
        Manager min=a[0];
        Manager max=a[0];

        for(int i=0;i<a.length;i++)
        {   
            if(min.getSalary()>a[i].getSalary()) min=a[i];
            if(max.getSalary()<a[i].getSalary()) max=a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }
    public static void maxminBonus(Manager[] a,Pair<? super Manager> result)
    {
        minmaxBonus(a,result);
        PairAlg.swapHelper(result);
    }
}
class PairAlg
{
    public static boolean hasNull(Pair<?> p)
    {
        return p.getFirst()==null&&p.getSecond()==null;
    }
    public static void swap(Pair<?> p){ swapHelper(p);}
    public static<T> void swapHelper(Pair<T> p)
    {
        T t=p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}