package core.inheritance;
import java.time.*;
public class TestEmployee
{
    public static void main(String[] args)
    {
        Manager boss=new Manager("aa",5000,1999,5,12);
        boss.setBonus(2000);
        Employee[] staff=new Employee[3];
        staff[0]=boss;
        staff[1]=new Employee("bb",4800,2000,10,15);
        staff[2]=new Employee("cc",2000,2000,10,20);
        for(Employee e:staff)
            System.out.println(e.getName()+" "+e.getSalary());
    }
}
