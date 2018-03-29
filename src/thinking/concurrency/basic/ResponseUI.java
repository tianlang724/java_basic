package thinking.concurrency.basic;
class UnResponseUI{
    private volatile double d=1;
    public UnResponseUI() throws Exception{
        while(d>0){
            d=d+(Math.PI+Math.E)/d;
        }
        System.in.read();
    }
}
public class ResponseUI extends Thread{
    private static volatile double d=1;
    public ResponseUI(){
        setDaemon(true);
        start();
    }
    public void run(){
        while(true){
            d=d+(Math.E+Math.PI)/d;
        }
    }
    public static void main(String[] args) throws Exception{
        //new ResponseUI();
        new UnResponseUI();
        System.in.read();
        System.out.println(d);
    }
}