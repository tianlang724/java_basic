package CodingGuide.List;

import CodingGuide.ListNode;

public class YueSefu {
    static public void main(String[] agrs){
        ListNode listNode=ListNode.createListRing(5);
        System.out.println(yuesefu(listNode,3).val);
        listNode=ListNode.createListRing(5);
        System.out.println(yueSeFuBetter(listNode,3).val);
    }
    static public ListNode  yuesefu(ListNode list, int m){
        if(list==null||list.next==list||m<1){
            return list;
        }
        while(true){
            int i=1;
            while (i<m-1){
                list=list.next;
                i++;
            }
            ListNode pre=list;
            if(pre==list.next){
                break;
            }
            pre.next=list.next.next;
            list=pre.next;
        }
        return list;
    }
    static public ListNode yueSeFuBetter(ListNode list,int m){
        if(list==null||list.next==list||m<1){
            return list;
        }
        int n=1;
        ListNode p=list;
        do{
            p=p.next;
            n++;
        }while(p!=list);
        int i,s=1;
        for (i = 2; i <= n; i++)
        {
            s = (s + m) % i;
        }
        i=1;
        p=list;
        while (i<s){
            p=p.next;
            i++;
        }
        p.next=p;
        return p;
    }
}
