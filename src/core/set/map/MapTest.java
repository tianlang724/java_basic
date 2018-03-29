package core.set.map;
import java.util.*;
import core.interfaces.clone.Employee;
public class MapTest{
    public static void main(String[] args){
        Map<String,Employee> staff=new HashMap<>();
        staff.put("144-25-5698",new Employee("aa",9990));
        staff.put("100-23-8756",new Employee("bb",2233));
        staff.put("789-43-3434",new Employee("df",8989));
        staff.put("321-43-8972",new Employee("ab",4900));
        staff.forEach((k,v)->System.out.println("key="+k+",value="+v));
        staff.remove("100-23-8756");
        staff.put("321-43-8972",new Employee("zz",8232));
        System.out.println(staff.get("321-43-8972"));
        staff.forEach((k,v)->System.out.println("key="+k+",value="+v));
    }
}