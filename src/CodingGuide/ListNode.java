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
    static public ListNode createListRing(int k){
        if(k<1){
            return null;
        }
        Random random=new Random();
        ListNode head=new ListNode(1);
        ListNode node=head;
        for(int i=2;i<=k;i++){
            node.next=new ListNode(i);
            node=node.next;
            if(i==k){
                node.next=head;
            }
        }
        return  head;
    }
}
