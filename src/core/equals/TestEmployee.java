package core.equals;
import java.time.*;
public class TestEmployee
{
    public static void main(String[] args)
    {
        Employee alice1=new Employee("Allice",7500,1988,12,15);
        Employee alice2=alice1;
        Employee alice3=new Employee("Allice",7500,1988,12,15);
        Employee bob=new Employee("Bob",7500,1998,2,5);
        System.out.println("alice1==alice2"+(alice1==alice2));
        System.out.println("alice1==alice3"+(alice1==alice3));
        System.out.println("alice1.equals(alice2)"+alice1.equals(alice2));
        System.out.println("alice1.equals(alice3)"+alice1.equals(alice3));
        System.out.println("alice1.equals(bob)"+alice1.equals(bob));
        Manager carl=new Manager("carl",8000,1988,10,10);
        Manager boss=new Manager("carl",8000,1988,10,10);
        boss.setBonus(5000);
        System.out.println("boss.toString="+boss);
        System.out.println("carls.equal(boss)"+carl.equals(boss));
        System.out.println("alice1.hashCode="+alice1.hashCode());
        System.out.println("alice3.hashCode="+alice3.hashCode());
        System.out.println("bob.hashCode="+bob.hashCode());
        System.out.println("carl.hashCode="+carl.hashCode());

        
    }
}
