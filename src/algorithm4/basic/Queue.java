package algorithm4.basic;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int count;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Queue(){
        last=null;
        first=null;
        count=0;
    }
    public void enqueue(Item item){
        Node node=new Node<Item>();
        node.item=item;
        node.next=null;
        count++;
        if(isEmpty()){
            first=node;
        }
        else{
            last.next=node;
        }
        last=node;
    }
    public Item dequeue(){
        if(isEmpty())  throw new NoSuchElementException("Queue underflow");
        Item ret=first.item;
        first=first.next;
        count--;
        if(count==0)
            first=null;
        return ret;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return count;
    }

    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private Queue.Node<Item> current;

        public ListIterator(Queue.Node<Item> first) {
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
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(Item item:this){
            sb.append(item);
            sb.append(" ");
        }
        //Iterable<Item> it=iterator();  why???
        sb.append('\n');
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue + " left on queue)");
    }
}
