package core.concurrency.synchkeyword;

public class SynchBankTest
{
    public static final int NACOUNTS=100;
    public static final double INTTIAL_BALANCE=1000;
    public static final double MAX_COUNT=1000;
    public static final int DELAY=10;

    public static void main(String[] args)
    {
        Bank bank=new Bank(NACOUNTS,INTTIAL_BALANCE);
        for(int i=0;i<NACOUNTS;i++){
            int from=i;
            Runnable r=()->{
                try{
                    while(true)
                    {
                        int toAcount=(int)(bank.size()*Math.random());
                        double amount=MAX_COUNT*Math.random();
                        bank.transfer(from,toAcount,amount);
                        Thread.sleep((int)(DELAY*Math.random()));
                    }
                }
                catch(InterruptedException e)
                {
                }
            };
            Thread t=new Thread(r);
            t.start();
        }


    }
}