package core.set.shuffle;
import java.sql.Connection;
import java.util.*;
public class ShuffleTest{
    public static void main(String[] args){
        List<Integer> numbers=new ArrayList<>();
        for(int i=1;i<=49;i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> winnerCombination=numbers.subList(0,6);
        Collections.sort(winnerCombination);
        System.out.println(winnerCombination);
    }
}