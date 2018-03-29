package offer;

public class GetNext {
    class Node{
        int value;
        Node left;
        Node right;
        Node father;
    }
    public static Node getNext(Node x){
        if(x==null) return null;
        if(x.right!=null){
            x=x.right;
            while(x.left!=null)
                x=x.left;
            return x;
        }
        if(x.father!=null&&x==x.father.left){
            return x.father;
        }
        while(x.father!=null&&x==x.father.right){
            x=x.father;
        }
        if(x.father!=null){
            return x.father;
        }
        return null;
    }
    public static void main(String[] args){

    }

}
