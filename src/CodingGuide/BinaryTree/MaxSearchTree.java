package CodingGuide.BinaryTree;

import CodingGuide.BinaryTreeNode;
import algorithm4.api.In;

/**
 * Created by zhang hui on /08/23/2018
 */

public class MaxSearchTree {
    public BinaryTreeNode maxSearchTree(BinaryTreeNode root){
        int[] leftParm=new int[4];
        int[] rightParm=new int[4];
        BinaryTreeNode left=maxSearchTree(root.left,leftParm);
        BinaryTreeNode right=maxSearchTree(root.right,rightParm);
        if(leftParm[1]<=root.val&&rightParm[0]>=root.val){
            return root;
        }else{
            if(leftParm[2]>rightParm[2]){
                return left;
            }else{
                return right;
            }
        }
    }
    private BinaryTreeNode maxSearchTree(BinaryTreeNode root,int[] parm){
        if(root.left==null&&root.right==null){
            parm[0]=root.val;
            parm[1]=root.val;
            parm[2]=1;
            parm[3]=1;
            return root;
        }
        int[] leftParm=new int[4];
        int[] rightParm=new int[4];
        BinaryTreeNode left=maxSearchTree(root.left,leftParm);
        BinaryTreeNode right=maxSearchTree(root.right,rightParm);
        if(leftParm[3]==1&&rightParm[3]==1&leftParm[1]<=root.val&&rightParm[0]>=root.val){
            parm[0]=leftParm[1];
            parm[1]=rightParm[0];
            parm[2]=leftParm[2]+rightParm[2]+1;
            parm[3]=1;
            return root;
        }else if(leftParm[3]==1&leftParm[1]<=root.val&&leftParm[2]+1>rightParm[2]){
            parm[0]=leftParm[1];
            parm[1]=root.val;
            parm[2]=leftParm[2]+1;
            parm[3]=1;
            return root;
        }else if(rightParm[3]==1&rightParm[1]>=root.val&&ri){
            parm[0]=root.val;
            parm[1]=rightParm[0];
            parm[2]=rightParm[2]+1;
            parm[3]=1;
            return root;
        }else{


        }

    }
}
