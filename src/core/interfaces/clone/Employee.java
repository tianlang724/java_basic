package core.interfaces.clone;
import java.util.Date;
import java.util.GregorianCalendar;
public class Employee implements Cloneable
{
    private String name;
    private double salary;
    private Date hireDay;
    public Employee(String name,double salary)
    {
        this.salary=salary;
        this.name=name;
        hireDay=new Date();
    }
    public String getName()
    {
        return name;
    }
    public double getSalary()
    {
        return salary;
    }
    public Date getHireDay()
    {
        return hireDay;
    }
    public void raiseSalary(double present){
        double raise=present*salary/100;
        salary+=raise;
    }
    public String toString(){
        return getClass().getName()+"[name="+name+",salary="+salary+",hireDay="+hireDay+"]";
    }
    public void setHireDay(int year,int month,int day){
        Date newdate=new GregorianCalendar(year,month-1,day).getTime();
        hireDay.setTime(newdate.getTime());
    }
    public Employee clone() throws CloneNotSupportedException{
        Employee cloned=(Employee) super.clone();
        cloned.hireDay=(Date)hireDay.clone();
        return cloned;
    }
}
