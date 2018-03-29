package algorithm4.solution.ch1;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;
import algorithm4.basic.Queue;

import java.util.Scanner;

public class Solution1_3_15 {
    public static void main(String[] arg){

        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        Queue<String> queue = new Queue<String>();
        while (scanner.hasNext()) {
            String item =scanner.next();
            queue.enqueue(item);
        }
        int size=queue.size();
        for(int i=0;i<size-n;i++){
            queue.enqueue(queue.dequeue());
        }
        StdOut.println(queue.dequeue());

        // ok
//        int n=StdIn.readInt();
//        Queue<String> queue = new Queue<String>();
//        while (!StdIn.isEmpty()) {
//            String item =StdIn.readString();
//            queue.enqueue(item);
//        }
//        int size=queue.size();
//        for(int i=0;i<size-n;i++){
//            queue.enqueue(queue.dequeue());
//        }
//        StdOut.println(queue.dequeue());

    }
}
