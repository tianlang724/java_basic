package algorithm4.basic;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;

public class WeightedUnionFind {
    private int[] id;
    private int[] sz;
    private int count;
    public WeightedUnionFind(int N){
        count=N;
        id=new int[N];
        sz=new int[N];
        for(int i=0;i<N;i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int count(){
        return count;
    }
    public int find(int p){
        while (p!=id[p]) p=id[p];
        return p;
    }
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }
    public void union(int p,int q){
        int pRoot=find(p);
        int qRoot=find(q);
        if(pRoot ==qRoot) return;
        if(sz[pRoot]<sz[qRoot]){
            sz[qRoot]+=sz[pRoot];
            id[p]=qRoot;
        }else{
            sz[pRoot]+=sz[qRoot];
            id[q]=pRoot;
        }
        count--;
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedUnionFind uf = new WeightedUnionFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
