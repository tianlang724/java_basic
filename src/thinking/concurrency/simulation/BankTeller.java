package thinking.concurrency.simulation;
import java.util.*;
import java.util.concurrent.*;
class Customer{
    private final int serviceTime;
    public Customer(int tm){serviceTime=tm;}
    public int getServiceTime(){return serviceTime;}
    public String toString(){
        return "["+serviceTime+"]";
    }
}
class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int maxLineSize){
        super(maxLineSize);
    }
    public String toString(){
        if(this.size()==0) return "[Empty]";
        StringBuilder sb=new StringBuilder();
        for(Customer cus:this)
            sb.append(cus);
        return sb.toString();
    }
}
class CustomerGenerator implements Runnable{
    private CustomerLine customers;
    private static Random rand=new Random(47);
    public CustomerGenerator(CustomerLine cq){
        customers=cq;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MICROSECONDS.sleep(rand.nextInt(10));
                customers.put(new Customer(rand.nextInt(8000)));
            }
        }catch(InterruptedException e){
            System.out.println("CustomerGenerator interrupt");
        }
        System.out.println("CustomerGenerator terminating");
    }
}
class Teller implements Runnable,Comparable<Teller>{
    private static int counter=0;
    private final int id=counter++;
    private CustomerLine customers;
    private int customerServed=0;
    private boolean servingCustomerLine=true;
    public Teller(CustomerLine cq){customers=cq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                Customer customer=customers.take();
                TimeUnit.MICROSECONDS.sleep(customer.getServiceTime());
                synchronized(this){
                    customerServed++;
                    while(!servingCustomerLine){
                        wait();
                    }
                }
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupt");
        }
        System.out.println(this+"terminating");
    }
    public synchronized void doSomethingElse(){
        customerServed=0;
        servingCustomerLine=false;
    }
    public synchronized void servCustomerLime(){
        assert !servingCustomerLine:"already serving:"+this;
        servingCustomerLine=true;
        notifyAll();
    }
    public String toString(){
        return "Teller:"+id;
    }
    public String shortString(){
        return "T:"+"id";
    }
    public synchronized int compareTo(Teller other){
        return customerServed<other.customerServed?-1:(customerServed==other.customerServed?0:1);
    }
}
class TellerManager implements Runnable{
    private ExecutorService es;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers=new PriorityQueue<>();
    private Queue<Teller> tellerDoingOtherthings=new LinkedList<>();
    private int adjustmentPeriod;
    private static Random rand=new Random(47);
    public TellerManager(ExecutorService es,CustomerLine cs,int adjustmentPeriod){
        this.es=es;
        this.customers=cs;
        this.adjustmentPeriod=adjustmentPeriod;
        Teller teller=new Teller(customers);
        es.execute(teller);
        workingTellers.add(teller);
    }
    public void adjustTellerNumber(){
        if(customers.size()/workingTellers.size()>2){
            if(tellerDoingOtherthings.size()>0){
                Teller teller=tellerDoingOtherthings.remove();
                teller.servCustomerLime();
                workingTellers.offer(teller);
                return;
            }
            Teller teller=new Teller(customers);
            es.execute(teller);
            workingTellers.add(teller);
            return;
        }
        if(workingTellers.size()>1&&customers.size()/workingTellers.size()<2)
            reassingOnTeller();
        if(customers.size()==0){
            while(workingTellers.size()>1)
                reassingOnTeller();
        }
    }
    private void reassingOnTeller(){
        Teller teller=workingTellers.poll();
        teller.doSomethingElse();
        tellerDoingOtherthings.offer(teller);
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MICROSECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers+"{");
                for(Teller teller:workingTellers){
                    System.out.print(teller+" ");
                }
                System.out.println("}"); 
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupt");
        }
        System.out.println(this+" terminating");
    }
    public String toString(){
        return "Tellermanager";
    }
}
public class BankTeller{
    static final int MAX_LINE_SIZE=50;
    static final int ADJSUMENT_PERIOD=500;
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        CustomerLine customers=new CustomerLine(MAX_LINE_SIZE);
        es.execute(new TellerManager(es, customers,ADJSUMENT_PERIOD));
        es.execute(new CustomerGenerator(customers));
        System.out.println("press enter to exit");
        System.in.read();
        es.shutdownNow();

    }
}