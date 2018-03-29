package core.set.treeset;
import java.util.*;
public class TreeTest
{
    public static void main(String[] args)
    {
        SortedSet<Item> parts=new TreeSet<>();
        parts.add(new Item("Toaster",1234));
        parts.add(new Item("Weight",40563));
        parts.add(new Item("Modem",9982));
        System.out.println(parts);

        NavigableSet<Item> sortByDecription=new TreeSet<>(
            Comparator.comparing(Item::getDescription));
        sortByDecription.addAll(parts);
        System.out.println(sortByDecription);
    }
}