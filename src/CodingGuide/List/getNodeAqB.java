package CodingGuide.List;

import CodingGuide.ListNode;

public class getNodeAqB {
    public static void main(String[] args){
        ListNode list=ListNode.createList(4);
        ListNode.printList(list);
        System.out.println(getMiddleNode(list).val);
        System.out.println(getAqBNode(list,1,3).val);
    }
    public static ListNode getMiddleNode(ListNode list){
        if(list==null)
            return null;
        ListNode p1=list;
        ListNode p2=list;
        while (p1.next!=null&&p1.next.next!=null){
            p1=p1.next.next;
            p2=p2.next;
        }
        return p2;
    }
    public static ListNode getAqBNode(ListNode list,int a,int b){
        if(list==null)
            return null;
        ListNode p1=list;
        ListNode p2=list;
        while (p1!=null){
            int ta=a;
            int tb=b;
            while (tb>0&&p1!=null){
                p1=p1.next;
                tb--;
            }
            if(p1==null||p1.next==null){
                break;
            }
            while (ta>0&&p2!=null){
                p2=p2.next;
                ta--;
            }
        }
        return p2;
    }
}
