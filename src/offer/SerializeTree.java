package offer;

import java.util.LinkedList;

public class SerializeTree {
    static public void main(String [] args){
        SerializeTree test=new SerializeTree();
        TreeNode node=new TreeNode(5);
        node.left=new TreeNode(8);
        node.right=new TreeNode(10);
        String s=test.Serialize(node);
        TreeNode x=test.Deserialize(s);
    }
    String Serialize(TreeNode root) {
        if(root==null)
            return "";
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        StringBuilder sb=new StringBuilder();
        while(!queue.isEmpty()){
            TreeNode node=queue.remove();
            if(node==null){
                sb.append('#');
                sb.append(" ");
            }else{
                sb.append(node.val);
                sb.append(" ");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return sb.toString();

    }
    TreeNode Deserialize(String str) {
        if(str==null||str.length()==0)
            return null;
        String[] ch=str.split(" ");
        LinkedList<TreeNode> queue=new LinkedList<>();
        TreeNode head=new TreeNode(Integer.parseInt(ch[0]));
        queue.add(head);
        int i=1;
        while(!queue.isEmpty()&&i<ch.length){
            TreeNode node=queue.remove();
            if(ch[i].equals("#")){
                TreeNode left=new TreeNode(Integer.parseInt(ch[i]));
                node.left=left;
                queue.add(left);
            }else{
                node.left=null;
            }
            i++;
            if(!ch[i].equals("#")){
                TreeNode right=new TreeNode(Integer.parseInt(ch[i]));
                node.right=right;
                queue.add(right);
            }else{
                node.right=null;
            }
            i++;
        }
        return head;
    }
}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
