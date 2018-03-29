package algorithm4.solution.ch1;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueRing<Item> implements Iterable<Item> {
    private Node<Item> last;    // last of queue
    private int count;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public QueueRing(){
        last=null;
        count=0;
    }
    public void enqueue(Item item){
        Node node=new Node<Item>();
        node.item=item;
        count++;
        if(last==null){
            last=node;
            last.next=node;
        }
        else {
            node.next = last.next;
            last.next = node;
            last = node;
        }
    }
    public Item dequeue(){
        if(isEmpty())  throw new NoSuchElementException("Queue underflow");
        Item ret=last.next.item;
        last.next=last.next.next;
        count--;
        if(count==0)
            last=null;
        return ret;
    }
    public boolean isEmpty(){
        return last==null;
    }
    public int size(){
        return count;
    }

    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(last);
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private QueueRing.Node<Item> current;

        public ListIterator(QueueRing.Node<Item> first) {
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
        QueueRing<String> queue = new QueueRing<String>();
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
