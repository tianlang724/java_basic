package algorithm4.find;

public class BST <Key extends Comparable<Key>,Value>{
    private Node root;
    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N;  //以该节点为根的子树的节点数

        public Node(Key key,Value value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }
    public int size(){
        return size(root);
    }
    public int size(Node node){
        if(node==null) return 0;
        else return node.N;
    }
    public Value get(Node node,Key key){
        if(node==null) return null;
        int cmp=key.compareTo(node.key);
        if(cmp<0) return get(node.left,key);
        else if(cmp>0) return get(node.right,key);
        else return node.value;
    }
    public Node put(Node node,Key key,Value val){
        if(node==null) return new Node(key,val,1);
        int cmp=key.compareTo(node.key);
        if(cmp<0) node.left=put(node.left,key,val);
        else if(cmp>0) node.right=put(node.right,key,val);
        else node.value=val;
        node.N=size(node.left)+size(node.right);
        return node;
    }
    //返回x为根节点的子树中小于x.key的键的数量
    public int rank(Key key){
        return rank(root,key);
    }
    private int rank(Node node,Key key){
        if(node==null) return 0;
        int cmp=key.compareTo(node.key);
        if(cmp<0) return rank(node.left,key);
        else if(cmp>0) return 1+size(node.left);
        else return size(node.left);
    }
    public Value select(int k){
        return select(root,k);
    }
    private Value select(Node node,int k){
        if(node==null) return null;
        int t=size(node.left);
        if(t>k) return select(node.left,k);
        else if(t<k) return select(node.right,k-t-1);
        else return node.left.value;
    }
    public void delete(Key key){
        root=delete(root,key);
    }
    private Node delete(Node node,Key key){
        if(node==null) return null;
        int cmp=key.compareTo(node.key);
        if(cmp<0) node.left=delete(node.left,key);
        else if(cmp>0) node.right=delete(node.right,key);
        else{
            if(node.right==null) return node.left;
            if(node.left==null) return node.right;
            Node t=node;
            node=min(node.right);
            node.right=deleteMin(t.right);
            node.left=t.left;
        }
        node.N=size(node.left)+size(node.right)+1;
        return node;
    }
    public Node min(){
        return min(root.left);
    }
    private Node min(Node node){
        if(node.left==null) return node;
        return min(node.left);
    }
    public void deleteMin(){
        root=deleteMin(root);
    }
    private Node deleteMin(Node node){
        if(node.left==null) return node.right;
        node.left=deleteMin(node.left);
        node.N=size(node.left)+size(node.right)+1;
        return node;
    }

}
