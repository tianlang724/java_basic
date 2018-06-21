package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class ReverseStack {
    static public void main(String[] args){
        LinkedList<Integer> stack=new LinkedList<>();
        stack.push(5);
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(7);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);

    }

    static private int getAndRemoveLastElement(LinkedList<Integer> stack){
        if(stack.isEmpty()){
            throw new RuntimeException("empty stack");
        }
        int temp=stack.pop();
        if (stack.isEmpty())
            return temp;
        int last=getAndRemoveLastElement(stack);
        stack.push(temp);
        return last;
    }
    static public void reverseStack(LinkedList<Integer> stack){
        if(stack.size()<=1)
            return;
        int last=getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(last);
    }

}
