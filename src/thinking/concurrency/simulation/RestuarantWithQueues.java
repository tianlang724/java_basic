package thinking.concurrency.simulation;
import thinking.enums.menu.*;
import java.util.concurrent.*;
import java.util.*;
class Order{
    private static int counter=0;
    private final int id=counter++;
    private final Customer2 customer;
    private final WaitPerson waitPerson;
    private final Food food;
    public Order(Customer2 cust,WaitPerson wp,Food f){
        customer=cust;
        waitPerson=wp;
        food=f;
    }
    public Food item(){return food;}
    public Customer2 getCustomer(){return customer;}
    public WaitPerson getWaitPerson(){return waitPerson;}
    public String toString(){
        return "Order:"+id+" item:"+ food+" for "+customer+" serverd by "+waitPerson;
    }
}
class Plate{
    private final Order order;
    private final Food food;
    public Plate (Order order,Food f){
        this.order=order;
        food=f;
    }
    public Order getOrder(){return order;}
    public Food getFood(){return food;}
    public String toString(){return food.toString();}
}
class Customer2 implements Runnable{
    private  static int counter=0;
    private final int id=counter++;
    private final WaitPerson waitPerson;
    private SynchronousQueue<Plate> plateSetting=new SynchronousQueue<>();
    public Customer2(WaitPerson wp){waitPerson=wp;}
    public void deliver(Plate plate) throws InterruptedException {
        plateSetting.put(plate);
    }
    public void run(){
        for(Course course:Course.values()){
 
            Food food=course.randomSelection();
            try{
                TimeUnit.MICROSECONDS.sleep(1000);
                waitPerson.placeOrder(this,food);
                System.out.println(this.toString()+" eating "+plateSetting.take());
            }catch(InterruptedException e){
                System.out.println(this.toString()+"  waiting for "+course+" Interrupt");
            }
        }
        System.out.println(this.toString()+" finished meal ,leaving");
    }
    public String toString(){return "Customer: "+id;}
}
class WaitPerson implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final Restuarant restuarant;
    BlockingQueue<Plate> filledOrders=new LinkedBlockingDeque<>();
    public WaitPerson(Restuarant restanrant){ this.restuarant=restanrant;}
    public void placeOrder(Customer2 cust,Food food){
        try{
            Order order=new Order(cust,this,food);
            System.out.println(order.toString());
            restuarant.orders.put(order);
        }catch(InterruptedException e){
            System.out.println(this.toString()+"  placeOrder interrupt");
        }
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                Plate plate =filledOrders.take();
                System.out.println(this.toString()+" received "+plate+" deliving to "+plate.getOrder().getCustomer().toString());
                plate.getOrder().getCustomer().deliver(plate);
            }
        }catch(InterruptedException e){
            System.out.println(this.toString()+" interrupt");
        }
        System.out.println(this.toString()+" off duty");
    }
    public String toString(){
        return "WaitPerson "+id;
    }
}
class Chef implements Runnable{
    private static int counter=0;    
    private final int id=counter++;
    private final Restuarant restuatant;
    private static Random rand=new Random(47);
    public Chef(Restuarant rest){restuatant=rest;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                Order order=restuatant.orders.take();
                Food requestedItem=order.item();
                TimeUnit.MICROSECONDS.sleep(rand.nextInt(100));
                Plate plate=new Plate(order,requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        }catch(InterruptedException e){
            System.out.println(this.toString()+" interrupt");
        }
        System.out.println(this.toString()+" off duty");
    }
    public String toString(){return "chef "+id;}
}
class Restuarant implements Runnable{
    private List<WaitPerson> waitPersons=new ArrayList<>();
    private List<Chef> chefs=new ArrayList<>();
    private ExecutorService es;
    private static Random rand=new Random(47);
    BlockingQueue<Order> orders=new LinkedBlockingQueue<>();
    public Restuarant(ExecutorService exec,int nWaitPersons,int nChefs){
        es=exec;
        for(int i=0;i<nWaitPersons;i++){
            WaitPerson waitperson=new WaitPerson(this);
            waitPersons.add(waitperson);
            es.execute(waitperson);
        }
        for(int i=0;i<nChefs;i++){
            Chef chef=new Chef(this);
            chefs.add(chef);
            es.execute(chef);
        }
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                WaitPerson wp=waitPersons.get(rand.nextInt(waitPersons.size()));
                Customer2 customer=new Customer2(wp);
                es.execute(customer);
                TimeUnit.MICROSECONDS.sleep(5000);
            }
        }catch(InterruptedException e){
            System.out.println("restuatant interrupt");
        }
        System.out.println("restuatant closing");
    }

}
public class RestuarantWithQueues{
    public static void main(String[] args) throws Exception{
        ExecutorService es=Executors.newCachedThreadPool();
        Restuarant restuatant=new Restuarant(es, 5, 4);
        es.execute(restuatant);
        System.out.println("press 'Enter' to exit");
        System.in.read();
        es.shutdownNow();
    }
}
    