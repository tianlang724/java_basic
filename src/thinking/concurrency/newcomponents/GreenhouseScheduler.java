package thinking.concurrency.newcomponents;
import java.util.concurrent.*;
import java.util.*;
import  java.util.Calendar;
public class GreenhouseScheduler{
    private volatile boolean light=false;
    private volatile boolean water=false;
    private String thermostat="Day";
    public synchronized String getThermostat(){return thermostat;}
    public synchronized void SetThermostat(String value){thermostat=value;}
    ScheduledThreadPoolExecutor scheduler=new ScheduledThreadPoolExecutor(10);
    public void schedule(Runnable event,long delay){
        scheduler.schedule(event, delay,TimeUnit.MICROSECONDS);
    }
    public void repeat(Runnable event,long initialDelay,long period){
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MICROSECONDS);
    }
    class LightOn implements Runnable{
        public void run(){
            System.out.println("truning on light");
            light=true;
        }
    }
    class LightOff implements Runnable{
        public void run(){
            System.out.println("truning off light");
            light=false;
        }
    }
    class WaterOn implements Runnable{
        public void run(){
            System.out.println("truning greenhouse water on");
            water=true;
        }
    }
    class WaterOff implements Runnable{
        public void run(){
            System.out.println("truning greenhouse water off");
            water=false;
        }
    }
    class ThermostatNight implements Runnable{
        public void run(){
            System.out.println("thermostat to night setting");
            thermostat="night";
        }
    }
    class ThermostatDay implements Runnable{
        public void run(){
            System.out.println("thermostat to day setting");
            thermostat="day";
        }
    }
    class Bell implements Runnable{
        public void run(){
            System.out.println("Bing....");
        }
    }
    class Terminate implements Runnable{
        public void run(){
            System.out.println("Terminating....");
            scheduler.shutdown();
            new Thread(){
                public void run(){
                    for(DataPoint d:data)
                        System.out.println(d);
                }
            }.start();
        }
    }
    static class DataPoint{
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint(Calendar c,float temperature,float humidity){
            time=c;
            this.temperature=temperature;
            this.humidity=humidity;
        }
        public String toString(){
            return time.getTime()+String.format(",temperature: %1$.1f humidity: $2$.1f",temperature,humidity);
        }
    }
    private Calendar lasttime=Calendar.getInstance();{
        lasttime.set(Calendar.MINUTE,30);
        lasttime.set(Calendar.SECOND,00);
    }
    private float lastTemp=65.0f;
    private int tempDirection=+1;
    private float lastHumidity=50.0f;
    private int humidityDirection=+1;
    private Random rand=new Random(47);
    List<DataPoint> data=Collections.synchronizedList(new ArrayList<>());
    class CollectData implements Runnable{
        public void run(){
            System.out.println("collecting data...");
            synchronized(GreenhouseScheduler.this){
                lasttime.set(Calendar.MINUTE,lasttime.get(Calendar.MINUTE)+30);
                if(rand.nextInt(5)==4)
                    tempDirection=-tempDirection;
                lastTemp=lastTemp+tempDirection*(1.0f+rand.nextFloat());
                if(rand.nextInt(5)==4)
                    humidityDirection=-humidityDirection;
                lastHumidity=lastHumidity+humidityDirection*(1.0f+rand.nextFloat());
                data.add(new DataPoint(lasttime,lastTemp,lastHumidity));
            }
        }
    }
    public static void main(String[] args){
        GreenhouseScheduler gh=new GreenhouseScheduler();
        gh.schedule(gh.new Terminate(),8000);
        gh.repeat(gh.new Bell(),0, 1000);
        gh.repeat(gh.new ThermostatNight(),0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(),0, 1400);
        gh.repeat(gh.new CollectData(),500, 500);
    }
}
