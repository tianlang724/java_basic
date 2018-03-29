package thinking.concurrency.shareresources;

import thinking.concurrency.shareresources.EvenChecker;

public class EvenGenetor extends IntGenerator{
    private int currentEvenValue=0;
    public  synchronized int next(){
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args){
        EvenChecker.test(new EvenGenetor());
    }
}