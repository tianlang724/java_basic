package core.interfaces;
import java.time.*;
public class Employee implements Comparable<Employee>
{
    private String name;
    private double salary;
    private LocalDate hireDay;
    public Employee(String name,double salary,int year,int month,int day)
    {
        this.salary=salary;
        this.name=name;
        hireDay=LocalDate.of(year,month,day);
    }
    public String getName()
    {
        return name;
    }
    public double getSalary()
    {
        return salary;
    }
    public LocalDate getHireDay()
    {
        return hireDay;
    }
    public void raiseSalary(double present){
        double raise=present*salary/100;
        salary+=raise;
    }
    public int compareTo(Employee other){
        return Double.compare(salary,other.salary);
    }
    public String toString(){
        return getClass().getName()+"[name="+name+",salary="+salary+",hireDay="+hireDay+"]";
    }
}
