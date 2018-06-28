package CodingGuide.List;

import CodingGuide.ListNode;

public class EveryKReverse {
    static public void main(String[] args){
        ListNode list=ListNode.createList(8);
        ListNode.printList(list);
        ListNode ret=reversEveryK(list,4);
        ListNode.printList(ret);
    }

    static public ListNode reversEveryK(ListNode list,int k){
        if(k<=1)
            return list;
        if(list==null||list.next==null)
            return list;
        int num=1;
        ListNode pre=list;
        ListNode cur=list;
        ListNode head=null;
        ListNode newpre=null;
        while (cur!=null){
            cur=cur.next;
            if(num%k==0){
                ListNode ret=reverseFromTo(pre,cur);
                if(head==null){
                    head=ret;
                    newpre=ret;
                }else{
                    while (newpre.next!=null){
                        newpre=newpre.next;
                    }
                    newpre.next=ret;
                }
                pre=cur;
            }
            num++;
        }
        if(num>0){
            while (newpre.next!=null){
                newpre=newpre.next;
            }
            newpre.next=pre;
        }
        return head;

    }
    static private ListNode reverseFromTo(ListNode list,ListNode to){
        if(list==null||list.next==null){
            return list;
        }
        ListNode pre=null;
        ListNode cur=list;
        ListNode next=null;
        while (cur!=to){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
}
