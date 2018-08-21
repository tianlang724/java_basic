package CodingGuide.List;

import CodingGuide.ListNode;
import thinking.net.mindview.simple.List;

import java.util.HashSet;

public class DeleteRepeatNode {
    static public void main(String[] args){
        ListNode list=ListNode.createListRepeat(10);
        ListNode.printList(list);
        ListNode.printList(deleteRepeatNode(list));
        list=ListNode.createListRepeat(15);
        ListNode.printList(list);
        ListNode.printList(deleteRepeatNodeNoHash(list));
    }
    static public ListNode deleteRepeatNode(ListNode list){
        if(list==null||list.next==null)
            return list;
        HashSet<Integer> set=new HashSet<>();
        ListNode p=list;
        ListNode newHead=null;
        ListNode pre=null;
        while (p!=null){
            if(!set.contains(p.val)){
                set.add(p.val);
                if(newHead==null){
                    newHead=p;
                    pre=p;
                }else{
                    pre.next=p;
                    pre=p;
                }
            }
            p=p.next;
        }
        pre.next=null;
        return newHead;
    }
    static public ListNode deleteRepeatNodeNoHash(ListNode list){
        if(list==null||list.next==null)
            return list;
        ListNode p1=list;
        ListNode pre=null;
        while (p1!=null){
            pre=p1;
            ListNode p2=p1.next;
            while (p2!=null){
                if(p2.val==p1.val){
                    pre.next=p2.next;
                }else{
                    pre=p2;
                }
                p2=p2.next;
            }
            p1=p1.next;
        }
        return list;
    }
}
