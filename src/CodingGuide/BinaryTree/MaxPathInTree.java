package CodingGuide.BinaryTree;

import CodingGuide.BinaryTreeNode;

/**
 * Created by zhang hui on /08/23/2018
 */

public class MaxPathInTree {
    static public void main(String[] args){
        BinaryTreeNode tree=BinaryTreeNode.createTree(15);
        BinaryTreeNode.printBinaryTree(tree);
        MaxPathInTree max=new MaxPathInTree();
        System.out.println("solution 1:"+max.maxPath(tree));
        max.noRetrunMaxPath(tree,0);
        System.out.println("solution 2:"+max.max);
    }
    public int maxPath(BinaryTreeNode root){
        return maxPath(root,0);
    }
    private int maxPath(BinaryTreeNode root,int curPath){
        if(root==null){
            return curPath;
        }
        curPath=curPath+root.val;
        int left=maxPath(root.left,curPath);
        int right=maxPath(root.right,curPath);
        return Math.max(left,right);
    }
    int max=Integer.MIN_VALUE;
    public void noRetrunMaxPath(BinaryTreeNode root,int curPath){
        if(root==null){
            if(curPath>max)
                max=curPath;
            return;
        }
        curPath=curPath+root.val;
        noRetrunMaxPath(root.left,curPath);
        noRetrunMaxPath(root.right,curPath);
    }
}
