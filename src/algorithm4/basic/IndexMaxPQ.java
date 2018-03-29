package algorithm4.basic;

import algorithm4.api.StdOut;

import java.util.NoSuchElementException;

public class IndexMaxPQ <Item extends Comparable<Item>> {
    private int maxN;
    private int count;
    private int[] pq;  //pq[i] 表示第i大的数字在item中的索引.i=1,2,.....
    private int[] qp;  //qp[i] item中第i个元素的在pq中的位置
    private Item[] items;
    public IndexMaxPQ(int maxN){
        items=(Item[])new Comparable[maxN+1];
        pq=new int[maxN+1];
        qp=new int[maxN+1];
        for(int i=0;i<=maxN;i++){
            qp[i]=-1;
        }
        count=0;
        this.maxN=maxN+1;
    }
    public void insert(int k ,Item item){

        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (contains(k)) throw new IllegalArgumentException("index is already in the priority queue");
        count++;
        qp[k] = count;
        pq[count] = k;
        items[k] = item;
        swim(count);

    }
    public void change(int k,Item item){
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);

    }
    public boolean contains(int k){
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        return qp[k]!=-1;
    }
    public void delete(int k){
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        int index=qp[k];
        exch(index,count--);
        sink(index);
        qp[k]=-1;
        items[k] = null;
    }
    public Item max() {
        if (count == 0) throw new NoSuchElementException("Priority queue underflow");
        return items[pq[1]];
    }
    public int maxIndex(){
        if (count== 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }
    public int delMax(){
        if (count== 0) throw new NoSuchElementException("Priority queue underflow");
        int ret=pq[1];
        exch(1,count--);
        sink(1);
        qp[ret]=-1;
        items[ret] = null;
        return ret;
    }
    public boolean isEmpty(){
        return count==0;
    }
    public int size(){
        return count;
    }
    private void swim(int k){
        while(k>1&&less(k/2,k)){
            exch(k,k/2);
            k=k/2;
        }
    }
    private void sink(int k){
        while(2*k<count){
            int j=2*k;
            if(j<count&&less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }
    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) <0;
    }
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMax();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();


    }
}
