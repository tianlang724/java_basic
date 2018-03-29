package algorithm4.basic;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;
/*
 use array
 */
public class MaxPQ <Key extends Comparable<Key>>{
    private Key[] pq;
    private int N=0;
    public MaxPQ(int maxN){
        pq=(Key[]) new Comparable[maxN+1];
    }
    public MaxPQ(){
        this(5);
    }
    public boolean isEmpty()
    {
        return N==0;
    }
    public int size(){
        return N;
    }
    public void insert(Key v){
        if (N == pq.length - 1) resize(2 * pq.length);
        pq[++N]=v;
        swim(N);
    }
    public Key delMax(){
        Key max=pq[1];
        exch(1,N--);
        pq[N+1]=null;
        sink(1);
        if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);
        return max;
    }
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }
    private void swim(int k){
        while(k>1&&less(k/2,k)){
            exch(k,k/2);
            k=k/2;
        }
    }
    private void sink(int k){
        while(2*k<=N){
            int j=2*k;
            if(j<N&&less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }
    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }

}
