package core.equals;
import java.time.*;
public class Manager extends Employee
{
    private double bonus;
    public Manager(String name,double salary,int year,int month,int day)
    {
        super(name,salary,year,month,day);
        bonus=0;
    }
    public void setBonus(double bonus)
    {
        this.bonus=bonus;
    }
    public double getSalary()
    {
        double base=super.getSalary();
        return base+bonus;
    }
    public boolean equals(Object otherObject){
        if(!super.equals(otherObject)) return false;
        Manager other=(Manager) otherObject;
        return bonus==other.bonus;
    }
    public int hashCode(){
        return super.hashCode()+17*Double.hashCode(bonus);
    }
    public String toString(){
        return super.toString()+"[bonus="+bonus+"]";
    }
}
