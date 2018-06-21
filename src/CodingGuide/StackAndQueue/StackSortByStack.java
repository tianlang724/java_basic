package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class StackSortByStack {
    static public void main(String[] args){
        LinkedList<Integer> stack=new LinkedList<>();
        stack.push(5);
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(7);
        System.out.println(stack);
        stackSort(stack);
        System.out.println(stack);
    }

    static public void stackSort(LinkedList<Integer> stack){
        if(stack.size()<=1)
            return;
        LinkedList<Integer> helpStack=new LinkedList<>();
        while(!stack.isEmpty()){
            int last=stack.pop();
            if(helpStack.isEmpty())
                helpStack.push(last);
            else{
                int k=0;
                while (!helpStack.isEmpty()&&helpStack.peek()<last){
                    stack.push(helpStack.pop());
                    k++;
                }
                helpStack.push(last);
            }
        }
        while (!helpStack.isEmpty()){
            stack.push(helpStack.pop());
        }
    }
}
