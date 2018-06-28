package CodingGuide;

import java.util.LinkedList;
import java.util.Random;

public class BinaryTreeNode {
    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode(int val){
        this.val=val;
    }
    static public void printBinaryTree(BinaryTreeNode root){
        if(root==null)
            return ;
        LinkedList<BinaryTreeNode> queue=new LinkedList<>();
        queue.add(root);
        int last=1;
        int cur=0;
        while(!queue.isEmpty()){
            BinaryTreeNode node=queue.remove();
            last--;
            if(node!=null){
                System.out.print(node.val+" ");
                queue.add(node.left);
                queue.add(node.right);
                cur+=2;
            }else{
                System.out.print("*  ");
            }
            if(last==0){
                System.out.println();
                last=cur;
                cur=0;
            }
        }
    }
    static public BinaryTreeNode createTree(int n){
        if (n <= 0)
            return null;
        Random random=new Random();
        if (n == 1)
            return new BinaryTreeNode(random.nextInt(50));
        BinaryTreeNode root=new BinaryTreeNode(random.nextInt(50));
        int leftNumber=random.nextInt(n-1);
        root.left=createTree(leftNumber);
        root.right=createTree(n-1-leftNumber);
        return root;

    }
}
