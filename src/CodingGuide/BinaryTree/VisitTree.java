package CodingGuide.BinaryTree;

import CodingGuide.BinaryTreeNode;

import java.util.LinkedList;

public class VisitTree {
    static public void main(String[] agrs){
        BinaryTreeNode tree=BinaryTreeNode.createTree(3);
        BinaryTreeNode.printBinaryTree(tree);
        System.out.println("pre order");
        preOrderRecursion(tree);
        System.out.println();
        preOrder(tree);
        System.out.println("mid order");
        midOrderRecursion(tree);
        System.out.println();
        midOrder(tree);
        System.out.println("post order");
        postOrderRecursion(tree);
        System.out.println();
        postOrder(tree);

    }
    static public void preOrderRecursion(BinaryTreeNode root){
        if(root==null)
            return;
        System.out.print(root.val+" ");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }
    static public void preOrder(BinaryTreeNode root){
        if(root==null)
            return;
        BinaryTreeNode p=null;
        LinkedList<BinaryTreeNode> stack=new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            p=stack.pop();
            System.out.print(p.val+" ");
            if(p.right!=null)
                stack.push(p.right);
            if(p.left!=null)
                stack.push(p.left);
        }
        System.out.println();

    }
    static public void midOrderRecursion(BinaryTreeNode root){
        if(root==null)
            return;
        midOrderRecursion(root.left);
        System.out.print(root.val+" ");
        midOrderRecursion(root.right);
    }
    static public void midOrder(BinaryTreeNode root){
        if(root==null)
            return;
        BinaryTreeNode p=root;
        LinkedList<BinaryTreeNode> stack=new LinkedList<>();
        while (!stack.isEmpty()||p!=null){
            while (p!=null){
                stack.push(p);
                p=p.left;
            }
            p=stack.pop();
            System.out.print(p.val+" ");
            p=p.right;
        }
        System.out.println();
    }
    static public void postOrderRecursion(BinaryTreeNode root){
        if(root==null)
            return;
        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        System.out.print(root.val+ " ");
    }
    static public void postOrder(BinaryTreeNode root){
        if(root==null)
            return;
        LinkedList<BinaryTreeNode> stack=new LinkedList<>();
        BinaryTreeNode pre=null;
        BinaryTreeNode p=null;
        stack.push(root);
        while (!stack.isEmpty()){
            p=stack.peek();
            if(p.left==null&&p.right==null||(pre!=null&&(pre==p.left||pre==p.right))){
                System.out.print(p.val+" ");
                pre=p;
                stack.pop();
            }else{
                if(p.right!=null)
                    stack.push(p.right);
                if(p.left!=null)
                    stack.push(p.left);
            }
        }
        System.out.println();
    }

}
