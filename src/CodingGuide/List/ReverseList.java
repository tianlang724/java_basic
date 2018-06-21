package CodingGuide.List;

import CodingGuide.ListNode;
import CodingGuide.ListNodeDouble;

public class ReverseList {
    public static void main(String[] args){
//        ListNode list2=ListNode.createList(5);
//        ListNode.printList(list2);
//        ListNode t=reverseList(list2);
//        ListNode.printList(t);

        ListNodeDouble list=ListNodeDouble.createDoubleList(5);
        ListNodeDouble.printList(list);
        ListNodeDouble t2=reverseDoubleList(list);
        ListNodeDouble.printList(t2);
    }
    static public ListNode reverseList(ListNode list){
        if(list==null||list.next==null){
            return list;
        }
        ListNode pre=null;
        ListNode cur=list;
        ListNode next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
    static public ListNodeDouble reverseDoubleList(ListNodeDouble list){
        if(list==null||list.next==null){
            return list;
        }
        ListNodeDouble pre=null;
        ListNodeDouble cur=list;
        ListNodeDouble next=null;
        while (cur!=null){
            next=cur.next;
            cur.pre=next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
}
