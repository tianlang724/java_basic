package algorithm4.sort;

import algorithm4.api.In;
import algorithm4.api.StdOut;
import algorithm4.basic.IndexMaxPQ;
import algorithm4.basic.IndexMinPQ;

public class Multiway {
    public static void merge(In[] streams){
        int N=streams.length;
        IndexMaxPQ<String> pq=new IndexMaxPQ<String>(N);
        for(int i=0;i<N;i++){
            if(!streams[i].isEmpty()){
                pq.insert(i,streams[i].readString());
            }
        }
        while(!pq.isEmpty()){
            StdOut.println(pq.maxIndex());
            int i=pq.delMax();
            if(!streams[i].isEmpty())
                pq.insert(i,streams[i].readString());
        }
    }
    public static void main(String[] args){
        int N=args.length;
        In[] streams=new In[N];
        for(int i=0;i<N;i++){
            streams[i]=new In(args[i]);
            merge(streams);
        }
    }
}
