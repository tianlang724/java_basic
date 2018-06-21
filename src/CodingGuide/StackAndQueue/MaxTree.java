package CodingGuide.StackAndQueue;


import CodingGuide.BinaryTreeNode;

import java.util.HashMap;
import java.util.LinkedList;

public class MaxTree {
    static public void main(String[] args){
        int[] t={3,5,20,1,7,10,4,9,2};
        BinaryTreeNode node=contructMaxTree(t);
        BinaryTreeNode.printBinaryTree(node);
    }
    static public BinaryTreeNode contructMaxTree(int arr[]){
        if(arr==null||arr.length==0)
            return null;
        int len=arr.length;
        BinaryTreeNode[] nodes=new BinaryTreeNode[len];
        HashMap<BinaryTreeNode,BinaryTreeNode> lmap=new HashMap<>();
        HashMap<BinaryTreeNode,BinaryTreeNode> rmap=new HashMap<>();
        LinkedList<BinaryTreeNode> stack=new LinkedList<>();
        for(int i=0;i<len;i++){
            nodes[i]=new BinaryTreeNode(arr[i]);
        }
        for(int i=0;i<len;i++){
            if(!stack.isEmpty()&&stack.peek().val<nodes[i].val){
                popStackSetMap(stack,lmap);
            }
            stack.push(nodes[i]);
        }
        while(!stack.isEmpty()){
            popStackSetMap(stack,lmap);
        }
        for(int i=0;i<len;i++){
            if(!stack.isEmpty()&&stack.peek().val<nodes[i].val){
                popStackSetMap(stack,rmap);
            }
            stack.push(nodes[i]);
        }
        while(!stack.isEmpty()){
            popStackSetMap(stack,rmap);
        }
        BinaryTreeNode head=null;
        for (int i=0;i<len;i++){
            BinaryTreeNode left=lmap.get(nodes[i]);
            BinaryTreeNode right=rmap.get(nodes[i]);
            if(left==null&&right==null){
                head=nodes[i];
            }else if(left==null){
                if(right.left==null){
                    right.left=nodes[i];
                }else{
                    right.right=nodes[i];
                }
            }else if(right==null){
                if(left.left==null){
                    left.left=nodes[i];
                }else{
                    left.right=nodes[i];
                }
            }else{
                BinaryTreeNode parent=left.val>right.val?left:right;
                if(parent.left==null){
                    parent.left=nodes[i];
                }else{
                    parent.right=nodes[i];
                }
            }
        }
        return head;


    }
    static public  void popStackSetMap(LinkedList<BinaryTreeNode> stack,HashMap<BinaryTreeNode,BinaryTreeNode> map){
        BinaryTreeNode popNode=stack.pop();
        if(stack.isEmpty()){
            map.put(popNode,null);
        }else{
            map.put(popNode,stack.peek());
        }
    }
}
