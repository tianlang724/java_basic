package CodingGuide;

import java.util.Random;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val){
        this.val=val;
    }
    static public void printList(ListNode list){
        while (list!=null){
            System.out.print(list.val+" ");
            list=list.next;
        }
        System.out.println();
    }
    static public ListNode createList(int k){
        if(k<1){
            return null;
        }
        Random random=new Random();
        ListNode head=new ListNode(random.nextInt(500));
        ListNode node=head;
        for(int i=0;i<k-1;i++){
            node.next=new ListNode(random.nextInt(500));
            node=node.next;
        }
        return  head;
    }
}
