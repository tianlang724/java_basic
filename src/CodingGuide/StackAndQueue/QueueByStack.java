package CodingGuide.StackAndQueue;

import algorithm4.api.In;
import algorithm4.basic.Queue;

import java.util.LinkedList;

public class QueueByStack {
    static  public  void main(String[] args){
        QueueByStack queue=new QueueByStack();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());
        queue.enqueue(3);
        System.out.println(queue.dequeue());
    }
    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;
    public QueueByStack(){
        stack1=new LinkedList<>();
        stack2=new LinkedList<>();
    }
    public void enqueue(Integer x){
        stack1.push(x);
    }
    public Integer dequeue(){
        if(stack1.isEmpty()&&stack2.isEmpty())
            throw new RuntimeException("empty queue");
        if(stack2.isEmpty()){
            while(stack1.size()>0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    public Integer peek(){
        if(stack1.isEmpty()&&stack2.isEmpty())
            throw new RuntimeException("empty queue");
        if(stack2.isEmpty()){
            while(stack1.size()>0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
}
