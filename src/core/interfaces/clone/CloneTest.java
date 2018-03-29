package core.interfaces.clone;
public class CloneTest{
    public static void main(String[] args){
        try{
            Employee original=new Employee("aa",5000);
            original.setHireDay(2000,10,10);
            Employee copy=original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002,8,19);
            System.out.println("original= "+original);
            System.out.println("copy= "+copy);
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}