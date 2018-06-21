package CodingGuide.List;

import CodingGuide.ListNode;

public class ReverseListFromTo {
    static public void main(String[] args){
        ListNode list=ListNode.createList(5);
        ListNode.printList(list);
        ListNode t=reverseListFromTo(list,2,5);
        ListNode.printList(t);

    }
    static public ListNode  reverseListFromTo(ListNode list,int from,int to){
        if(list==null||list.next==null)
            return list;
        int x=1;
        ListNode cur=list;
        while (x<from-1&&cur!=null){
            cur=cur.next;
            x++;
        }
        if(cur==null||cur.next==null){
            return list;
        }
        ListNode pre=cur;
        ListNode fromNode=cur.next;
        while (x<to&&cur!=null){
            cur=cur.next;
            x++;
        }
        ListNode toNode=cur;
        ListNode toNodeNext=null;
        if(cur!=null){
            //从from开始的全部翻转
            toNodeNext=cur.next;
        }
        if(from==1){
            ListNode node=reverseHelper(list,toNode);
            list.next=toNodeNext;
            return node;
        }
        pre.next=reverseHelper(fromNode,toNode);
        fromNode.next=toNodeNext;
        return list;
    }
    static private ListNode reverseHelper(ListNode from,ListNode to){
        if(from==null||from.next==null){
            return from;
        }
        ListNode pre=null;
        ListNode cur=from;
        ListNode next=null;
        while (pre!=to&&cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;

    }

}
