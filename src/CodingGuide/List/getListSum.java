package CodingGuide.List;

import CodingGuide.ListNode;
import algorithm4.api.In;
import thinking.net.mindview.simple.List;

import java.util.LinkedList;

public class getListSum {
    public static void main(String[] args){
        ListNode list1=ListNode.createList(10);
        ListNode list2=ListNode.createList(8);
        ListNode.printList(list1);
        ListNode.printList(list2);
        ListNode ret=getListSumStack(list1,list2);
        ListNode.printList(ret);

        ListNode ret2=gegetListSumReverse(list1,list2);
        ListNode.printList(ret2);
    }

    static public ListNode getListSumStack(ListNode list1,ListNode list2){
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        LinkedList<Integer> stack1=new LinkedList<>();
        LinkedList<Integer> stack2=new LinkedList<>();
        ListNode p=list1;
        while (p!=null){
            stack1.push(p.val);
            p=p.next;
        }
        p=list2;
        while (p!=null){
            stack2.push(p.val);
            p=p.next;
        }
        ListNode next=null;
        ListNode node=null;
        int carry=0;
        while (!stack1.isEmpty()||!stack2.isEmpty()){
            int t=carry;
            if(!stack1.isEmpty())
                t+=stack1.pop();
            if(!stack2.isEmpty())
                t+=stack2.pop();
            carry=t/10;
            t=t%10;
            node=new ListNode(t);
            node.next=next;
            next=node;
        }
        if(carry>0){
            node=new ListNode(carry);
            node.next=next;
        }
        return node;
    }
    static public ListNode gegetListSumReverse(ListNode list1,ListNode list2){
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        ListNode reverse1=reverseHelper(list1);
        ListNode reverse2=reverseHelper(list2);
        ListNode next=null;
        ListNode node=null;
        int carry=0;
        while (reverse1!=null||reverse2!=null){
            int t=carry;
            if(reverse1!=null){
                t+=reverse1.val;
                reverse1=reverse1.next;
            }
            if(reverse2!=null){
                t+=reverse2.val;
                reverse2=reverse2.next;
            }
            carry=t/10;
            t=t%10;
            node=new ListNode(t);
            node.next=next;
            next=node;
        }
        if(carry>0){
            node=new ListNode(carry);
            node.next=next;
        }
        return node;

    }
    static private ListNode reverseHelper(ListNode list){
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
}
