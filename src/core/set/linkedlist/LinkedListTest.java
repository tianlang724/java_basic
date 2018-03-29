package core.set.linkedlist;
import java.util.*;
public class LinkedListTest{
    public static void main(String[] args){
        List<String> a=new LinkedList<>();
        a.add("Army");
        a.add("Carl");
        a.add("Eelic");

        List<String> b=new LinkedList<>();
        b.add("Bob");
        b.add("Dog");
        b.add("Frans");
        b.add("Gold");

        ListIterator<String> aIt=a.listIterator();
        Iterator<String> bIt=b.iterator();
        while(bIt.hasNext()){
            if(aIt.hasNext()) aIt.next();
            aIt.add(bIt.next());
        }
        System.out.println(a);
        bIt=b.iterator();
        while(bIt.hasNext()){
            bIt.next();
            if(bIt.hasNext()){
                bIt.next();
                bIt.remove();
            }
        }
        System.out.println(b);
        a.removeAll(a);
        System.out.println(a);
    }
}