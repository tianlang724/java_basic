package concurrency_art;
public class NoVisibility{
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        public void run(){
            while(!ready)
                Thread.yield();
            System.out.println("number="+number);
        }
    }
    public static void main(String[] args){
        new ReaderThread().start();
        number=12;
        ready=true;
    }
}