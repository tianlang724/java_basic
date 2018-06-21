package CodingGuide.List;

import CodingGuide.ListNode;
import CodingGuide.ListNodeDouble;

public class CountBackK {
    public static void main(String[] args){
        ListNodeDouble list=ListNodeDouble.createDoubleList(2);
        ListNodeDouble.printList(list);
        list=deleteCountBackK(list,4);
        ListNodeDouble.printList(list);

        ListNode list2=ListNode.createList(3);
        ListNode.printList(list2);
        ListNode t=deleteCountBackK(list2,2);
        ListNode.printList(t);
    }
    static public ListNodeDouble deleteCountBackK(ListNodeDouble list,int k){
        if(list==null||k<1)
            return  null;
        ListNodeDouble p1=list;
        ListNodeDouble p2=list;
        int t=1;
        while(p1!=null&&t<k){
            p1=p1.next;
            t++;
        }
        if(t<k){
            return list;
        }
        if(t==k&&p1.next==null){
            list.next.pre=null;
            return list.next;
        }
        while(p1!=null){
            p1=p1.next;
            p2=p2.next;
        }
        ListNodeDouble pre=p2.pre;
        ListNodeDouble next=p2.next;
        pre.next=next;
        next.pre=pre;
        return list;
    }
    public static ListNode deleteCountBackK(ListNode list, int k){
        if(list==null||k<1)
            return list;
        int len=1;
        ListNode p1=list;
        ListNode p2=list;
        while (p1!=null&&len<k){
            p1=p1.next;
            len++;
        }
        if(len<k){
            return list;
        }
        if(p1.next==null&&len==k){
            return list.next;
        }
        while (p1.next!=null&&p1.next.next!=null){
            p1=p1.next;
            p2=p2.next;
        }

        p2.next=p2.next.next;
        return list;
    }
}
