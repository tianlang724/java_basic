//package concurrency_art.database;
//
//import java.util.LinkedList;
//
//
//public class ConnectionPool{
//    private LinkedList<Connection> pool =new LinkedList<>();
//    public ConnectionPool(int initialSize){
//        if(initialSize>0){
//            for(int i=0;i<initialSize;i++){
//                pool.addLast(ConnectionDriver.createConnection());
//            }
//        }
//    }
//}