package algorithm4.basic;

import algorithm4.api.StdIn;
import algorithm4.api.StdOut;

public class UnionFind {
    private int[] id;
    private int count;
    public UnionFind(int N){
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++)
            id[i]=i;
    }
    public void DF_union(int p,int q){
        int pid=QF_find(p);
        int qid=QF_find(q);
        if(pid==qid)
            return;
        for(int i=0;i<id.length;i++){
            if(id[i]==qid) id[i]=pid;
        }
        count--;
    }
    public int QF_find(int p){
        return id[p];
    }
    public boolean QF_connected(int p,int q){
        return QF_find(p)==QF_find(q);
    }
    public int QU_find(int p){
        while(p!=id[p]) p=id[p];
        return p;
    }
    public void QU_union(int p,int q){
        int pRoot=QU_find(p);
        int qRoot=QU_find(q);
        if(pRoot==qRoot) return;
        id[pRoot]=id[qRoot];
        count--;
    }
    public boolean QU_connected(int p,int q){
        return QU_find(p)==QU_find(q);
    }
    public int count(){
        return  count;
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionFind uf = new UnionFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.QU_connected(p, q)) continue;
            uf.QU_union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
