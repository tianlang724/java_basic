package CodingGuide.List;

import CodingGuide.ListNode;
import thinking.net.mindview.simple.List;

public class ReConsitruct {
    static public void main(String[] args){
        ListNode node= ListNode.createList(10);
        ListNode.printList(node);
        ListNode ret=reConstruct(node);
        ListNode.printList(ret);
    }
    static public ListNode reConstruct(ListNode list){
        if(list==null)
            return null;
        int num=0;
        ListNode p=list;
        while (p!=null){
            num++;
            p=p.next;
        }
        if(num<4)
            return list;
        ListNode p2=list;
        int k=num/2;
        while (k>0){
            p2=p2.next;
            k--;
        }
        p=list;
        ListNode mid=p2;
        ListNode next=null;
        while (p.next!=mid){
            next=p.next;
            p.next=p2;
            p2=p2.next;
            p=p.next;
            p.next=next;
            p=p.next;
        }
        p.next=p2;
        return list;


    }
}
