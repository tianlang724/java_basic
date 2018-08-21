package CodingGuide.List;

import CodingGuide.BinaryTreeNode;
import algorithm4.solution.ch1.DoubleNode;

import java.util.LinkedList;

public class TreeToDoubleList {
    static public BinaryTreeNode treeToDoubleList(BinaryTreeNode root){
        if(root==null)
            return null;
        BinaryTreeNode head=null;
        if(root.left!=null){
            head=treeToDoubleList(root.left);
            BinaryTreeNode p=head;
            while (p.right!=null){
                p=p.right;
            }
            p.right=root;
            root.left=p;
        }
        if(root.right!=null){
            BinaryTreeNode node=treeToDoubleList(root.right);
            root.right=node;
            node.left=root;
        }
        return  head==null?root:head;
    }
    static public BinaryTreeNode treeToDoubleListNoRecursion(BinaryTreeNode root){
        if(root==null)
            return null;
        LinkedList<BinaryTreeNode> stack=new LinkedList<>();
        BinaryTreeNode p=root;
        BinaryTreeNode head=null;
        BinaryTreeNode pre=null;
        while (!stack.isEmpty()||p!=null){
            while (p!=null){   //此处条件必须为p!=null, p.left!=null不可以
                stack.push(p);
                p=p.left;
            }
            p=stack.pop();
            if(head==null){
                head=p;
                pre=p;
            }else{
                p.left=pre; //注意是双向链表
                pre.right=p;
                pre=p;
            }
            p=p.right;
        }
        return head;
    }

}

