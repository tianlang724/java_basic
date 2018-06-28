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
        ListNode head=new ListNode(random.nextInt(100));
        ListNode node=head;
        for(int i=0;i<k-1;i++){
            node.next=new ListNode(random.nextInt(100));
            node=node.next;
        }
        return  head;
    }
    static public ListNode createListRepeat(int k){
        if(k<1){
            return null;
        }
        Random random=new Random();
        ListNode head=new ListNode(random.nextInt(5));
        ListNode node=head;
        for(int i=0;i<k-1;i++){
            node.next=new ListNode(random.nextInt(5));
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
            node.next=new ListNode(2*i);
            node=node.next;
            if(i==k){
                node.next=head;
            }
        }
        return  head;
    }
}
