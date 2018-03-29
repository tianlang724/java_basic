package core.interfaces;
import java.util.*;
public class EmployeeSortTest{
    public static void main(String[] args){
        Employee[] staff=new Employee[3];
        staff[0]=new Employee("aa",4500,1999,12,12);
        staff[1]=new Employee("bb",8500,1995,1,12);
        staff[2]=new Employee("cc",1200,2000,10,3);
        Arrays.sort(staff);
        for(Employee e:staff)
            System.out.println(e);
    }
}