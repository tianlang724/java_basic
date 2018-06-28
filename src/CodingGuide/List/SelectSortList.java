package CodingGuide.List;

import CodingGuide.ListNode;

public class SelectSortList {
    static public void main(String[] args){
        ListNode list=ListNode.createList(10);
        ListNode.printList(list);
        ListNode sort=selectSort(list);
        ListNode.printList(sort);
    }

    static public ListNode selectSort(ListNode list){
        if(list==null)
            return list;
        ListNode min=null,minPre=null;
        ListNode p=null,pre=null;
        ListNode cur=list;
        ListNode head=null,sortCur=null;
        while (cur!=null){
            min=cur;
            minPre=null;
            pre=cur;
            p=cur.next;
            while (p!=null){
                if(p.val<min.val){
                    min=p;
                    minPre=pre;
                }
                pre=p;
                p=p.next;

            }
            if(minPre==null){
                cur=min.next;
            }else{
                minPre.next=min.next;
            }
            if(head==null){
                head=min;
                sortCur=head;
            }else{
                sortCur.next=min;
                sortCur=min;
            }

        }
        return head;

    }

}
