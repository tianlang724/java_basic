package core.reflection;
import core.inheritance.Employee;
import java.util.ArrayList;
public class ObjectAnalyzerTest{
    public static void main (String[] args){
        ArrayList<Integer> squares=new ArrayList<>();
        for(int i=1;i<=5;i++){
            squares.add(i*i);
        }
        Employee boss=new Employee("aa",5000,1999,5,12);
        System.out.println(new ObjectAnalyzer().toString(boss));
    }
}