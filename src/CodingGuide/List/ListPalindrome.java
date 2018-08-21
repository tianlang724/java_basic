package CodingGuide.List;

import CodingGuide.ListNode;

import java.util.LinkedList;

public class ListPalindrome {
    static public void main(String[] args){
        ListNode list=ListNode.createList(5);
        System.out.println(isPalindrom(list));
        ListNode parList=ListNode.createListParlindrom(6);
        System.out.println(isPalindrom2(parList));
    }

    static public boolean isPalindrom(ListNode list){
        if(list==null||list.next==null)
            return true;
        LinkedList<ListNode> stack=new LinkedList<>();
        ListNode p=list;
        while (p!=null){
            stack.push(p);
            p=p.next;
        }
        p=list;
        while (p!=null&&p.val==stack.peek().val){
            p=p.next;
            stack.pop();
        }
        if(p==null)
            return true;
        return  false;
    }
    static public boolean isPalindrom2(ListNode list){
        if(list==null||list.next==null)
            return true;
        int num=0;
        ListNode p=list;
        while (p!=null){
            p=p.next;
            num++;
        }
        LinkedList<ListNode> stack=new LinkedList<>();
        int k=num/2;
        int i=1;
        p=list;
        while (i<=k){
            stack.push(p);
            p=p.next;
            i++;
        }
        if(num%2==1)
            p=p.next;
        while (p!=null&&p.val==stack.peek().val){
            p=p.next;
            stack.pop();
        }
        if(p==null)
            return true;
        return  false;
    }
    static public boolean isPalindrom3(ListNode list){
        if(list==null||list.next==null)
            return true;
        int num=0;
        ListNode p=list;
        while (p!=null){
            p=p.next;
            num++;
        }
        int k=num/2;
        int i=1;
        p=list;
        while (i<=k){
            p=p.next;
            i++;
        }
        if(k%2==1){
            p=p.next;
        }
        ListNode back=reverseList(p);
        p=list;
        while (p!=null&&back!=null&&p.val==back.val){
            p=p.next;
            back=back.next;
        }
        if(back==null)
            return true;
        return false;
    }
    static private ListNode reverseList(ListNode list){
        if(list==null||list.next==null)
            return list;
        ListNode p=list;
        ListNode pre=null;
        ListNode next=null;
        while (p!=null){
            next=p.next;
            p.next=pre;
            pre=p;
            p=next;
        }
        return pre;
    }
}
