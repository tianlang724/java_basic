package thinking.concurrency.simulation;
import java.util.*;
import java.util.concurrent.*;
class Car{
    private final int id;
    boolean engine=false,driverTrain=false,wheels=false;
    public Car(int id){this.id=id;}
    public Car(){id=-1;}
    public synchronized int getId(){return id;}
    public synchronized void addEngine(){engine=true;}
    public synchronized void addDriverTrian(){driverTrain=true;}
    public synchronized void addWheels(){wheels=true;}
    public synchronized String toString(){
        return "Car: "+id+"[engine:"+engine+",driverTrian:"+driverTrain+",wheels:"+wheels+"]";
    }
}
class CarQueue extends LinkedBlockingDeque<Car>{}
class ChassisBuilder implements Runnable{
    private CarQueue carQueue;
    private int counter=0;
    public ChassisBuilder(CarQueue carq){carQueue=carq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MICROSECONDS.sleep(500);
                Car c=new Car(counter++);
                System.out.println("ChassisBuilder created "+c);
                carQueue.put(c);
            }
        }catch(InterruptedException e){
            System.out.println("ChassisBuilder interrupt");
        }
        System.out.println("ChassisBuilder off");
    }
}
class Assembler implements Runnable{
    private CarQueue   chassisQueue,finishQueue;
    private Car car;
    private CyclicBarrier barrier=new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assembler(CarQueue carq,CarQueue fq,RobotPool rp){
        chassisQueue=carq;
        finishQueue=fq;
        robotPool=rp;
    }
    public Car car(){
        return car;
    }
    public CyclicBarrier getBarrier(){return barrier;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                car=chassisQueue.take();
                robotPool.hire(EngineRobot.class,this);
                robotPool.hire(DriverTrianRobot.class,this);
                robotPool.hire(WheelRobot.class,this);
                barrier.await();
                finishQueue.put(car);
            }
        }catch(InterruptedException e){
            System.out.println("Assembler interrupt");
        }catch(BrokenBarrierException e){}
        System.out.println("Assembler off");
    }
}
class Reporter implements Runnable{
    private CarQueue carQueue;
    public Reporter(CarQueue carq){carQueue=carq;}
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println(carQueue.take());
            }
        }catch(InterruptedException e){
            System.out.println("Reporter interrupt");
        }
        System.out.println("Reporter off");
    }
}
abstract class Robot implements Runnable{
    private RobotPool pool;
    public Robot(RobotPool pool){this.pool=pool;}
    protected Assembler assembler;
    public  Robot assignAssembler(Assembler ass){assembler=ass;return this;}
    private boolean engage=false;
    public synchronized void engage(){
        engage=true;
        notifyAll();
    }
    abstract protected void performService();
    public void run(){
        try{
            powerDown();
            while(!Thread.interrupted()){
                performService();
                assembler.getBarrier().await();
                powerDown();
            }
        }catch(InterruptedException e){
            System.out.println(this+ "interrupt");
        }catch(BrokenBarrierException e){
            throw new RuntimeException();
        }
        System.out.println(this+" off");
    }
    private synchronized void powerDown() throws InterruptedException{
        engage=false;
        assembler=null;
        pool.release(this);
        while(engage==false){
            wait();
        }
    }
    public String toString(){
        return getClass().getName();
    }
}
class EngineRobot extends Robot{
    public EngineRobot(RobotPool pool){super(pool);}
    protected void performService(){
        System.out.println(this+ " is installing engine");
        assembler.car().addEngine();
    }
}
class DriverTrianRobot extends Robot{
    public DriverTrianRobot(RobotPool pool){super(pool);}
    protected void performService(){
        System.out.println(this+ " is installing drivertrain");
        assembler.car().addDriverTrian();
    }
}
class WheelRobot extends Robot{
    public WheelRobot(RobotPool pool){super(pool);}
    protected void performService(){
        System.out.println(this+ " is installing wheelrobot");
        assembler.car().addWheels();
    }
}
class RobotPool{
    private Set<Robot> pool=new HashSet<>();
    public synchronized void add(Robot r){
        pool.add(r);
        notifyAll();
    }
    public synchronized void hire(Class<? extends Robot> robotType,Assembler ass) throws InterruptedException{
        for(Robot r:pool){
            if(r.getClass().equals(robotType)){
                pool.remove(r);
                r.assignAssembler(ass);
                r.engage();
                return;
            }
        }
        wait();
        hire(robotType,ass);
    }
    public synchronized void release(Robot r){add(r);}
}
public class CarBuilder{
    public static void main(String[] args) throws InterruptedException{
        CarQueue chassisQueue=new CarQueue();
        CarQueue finishedQueue=new CarQueue();
        ExecutorService es=Executors.newCachedThreadPool();
        RobotPool robotpool=new RobotPool();
        es.execute(new EngineRobot(robotpool));
        es.execute(new DriverTrianRobot(robotpool));
        es.execute(new WheelRobot(robotpool));
        es.execute(new Assembler(chassisQueue, finishedQueue,robotpool));
        es.execute(new Reporter(finishedQueue));
        es.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        es.shutdownNow();
    }
}