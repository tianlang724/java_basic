package CodingGuide.List;

import CodingGuide.ListNode;

public class deleteGivenNode {
    static public void main(String[] args){
        ListNode list=ListNode.createListRepeat(10);
        ListNode.printList(list);
        ListNode ret=deleteGivenNode(list,3);
        ListNode.printList(ret);

    }
    static public ListNode deleteGivenNode(ListNode list, int val){
        if(list==null)
            return null;
        ListNode p=list;
        ListNode head=null;
        ListNode pre=null;
        while (p!=null){
            if(p.val==val){
                if(pre!=null){
                    pre.next=p.next;
                }
            }else{
                if(head==null){
                    head=p;
                    pre=p;
                }
                pre=p;
            }
            p=p.next;
        }
        return head;
    }
}
