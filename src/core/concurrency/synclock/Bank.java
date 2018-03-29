package core.concurrency.synclock;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.locks.*;

public class Bank
{
    private final double[] accounts;
    private Lock banklock;
    private Condition suffientFunds;


    public Bank(int n,double initialBalance)
    {
        accounts=new double[n];
        Arrays.fill(accounts,initialBalance);
        banklock=new ReentrantLock();
        suffientFunds=banklock.newCondition();
    }
    public void transfer(int from,int to,double amount) throws InterruptedException
    {
        //if(accounts[from]<amount) return;
        banklock.lock();
        try{
            /*不能放在前面的位置，否则检查没有问题之后被中断，之后继续执行时可能余额已经不足
            if(accounts[from]<amount) return; 
            */
            //改进
            while(accounts[from]<amount){
                suffientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from]-=amount;
            System.out.printf("%10.2f from %d to %d ,",amount,from,to);
            accounts[to]+=amount;
            System.out.printf("Total balance:%10.2f%n",getTotalBanlance());
            suffientFunds.signal();
        }
        finally{
            banklock.unlock();
        }
    }
    public double getTotalBanlance()
    {
        banklock.lock();
        try{
            double sum=0;
            for(double a:accounts)
                sum+=a;
            return sum;
        }
        finally{
            banklock.unlock();
        }

    }
    public int size(){
        return accounts.length;
    }
}