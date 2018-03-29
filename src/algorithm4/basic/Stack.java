package algorithm4.basic;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;
import com.sun.deploy.panel.ITreeNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;     // top of stack
    private int count;                // size of the stack

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Stack(){
        first=null;
        count=0;
    }
    public void push(Item item){
        Node node=new Node<Item>();
        node.item=item;
        node.next=null;
        if(first!=null)
            node.next=first;
        first=node;
        count++;
    }
    public Item pop(){
        Item ret=first.item;
        first=first.next;
        count--;
        return ret;

    }
    public Item peek(){
        Item ret=first.item;
        return ret;

    }
    public boolean isEmpty(){
        return  first==null;
    }
    public int size(){
        return count;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(Item item:this){
            sb.append(item);
            sb.append(',');
        }
        sb.append('\n');
        return sb.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private Stack.Node<Item> current;

        public ListIterator(Stack.Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
