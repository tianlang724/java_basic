package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class StackGetMin {
    static public void main(String[] args){
        StackGetMin stack=new StackGetMin();
        stack.push(5);
        stack.push(3);
        System.out.println(stack.getMin());
        stack.push(1);
        stack.push(6);
        stack.push(7);
        System.out.println(stack.getMin());

    }
    LinkedList<Integer> stack;
    LinkedList<Integer> stackMin;
    public StackGetMin(){
        stack=new LinkedList<>();
        stackMin=new LinkedList<>();
    }
    public Integer pop(){
        if(stack.isEmpty()){
            throw new RuntimeException("stack empty");
        }
        stackMin.pop();
        return  stack.pop();

    }
    public void push(Integer x){
        if(stackMin.isEmpty()){
            stackMin.push(x);
        }else{
            if(x>stackMin.peek()){
                stackMin.push(stackMin.peek());
            }else{
                stackMin.push(x);
            }

        }
        stack.push(x);
    }
    public Integer getMin(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("stack empty");
        }
        return stackMin.peek();

    }

}
