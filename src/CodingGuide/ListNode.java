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

    static public ListNode createListParlindrom(int k){
        if(k<1){
            return null;
        }
        int m=k/2;
        ListNode head=null;
        ListNode pre=null;
        for(int i=1;i<=m;i++){
            ListNode node=new ListNode(i);
            if(head==null){
                head=node;
                pre=node;
            }else{
                pre.next=node;
                pre=node;
            }
        }
        if(k%2==1){
            ListNode node=new ListNode(m+1);
            pre.next=node;
            pre=node;
        }
        for(int i=m;i>0;i--){
            ListNode node=new ListNode(i);
            pre.next=node;
            pre=node;
        }
        return  head;
    }
}
