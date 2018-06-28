package CodingGuide.List;

import java.util.HashMap;

public class CopySpecList {
    static public RandomListNode copyList1(RandomListNode list){
        if(list==null)
            return null;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode head=null;
        RandomListNode pre=null;
        RandomListNode cur=null;
        RandomListNode p=list;
        while (p!=null){
            cur=new RandomListNode(p.label);
            map.put(p,cur);
            if(head==null){
                head=cur;
                pre=cur;
            }else{
                pre.next=cur;
                pre=cur;
            }
            p=p.next;
        }
        p=list;
        cur=head;
        while (p!=null){
            cur.random=map.get(p.random);
            cur=cur.next;
            p=p.next;
        }
        return head;
    }
    static public RandomListNode copyList2(RandomListNode list){
        if(list==null)
            return null;
        RandomListNode p=list;
        RandomListNode next=null;
        while (p!=null){
            RandomListNode node=new RandomListNode(p.label);
            next=p.next;
            p.next=node;
            node.next=next;
            p=next;
        }
        p=list;
        while (p!=null){
            next=p.next;
            if(p.random!=null)
                next.random=p.random.next;
            p=next.next;
        }
        RandomListNode head=null;
        RandomListNode newcur=null;
        p=list;
        while (p!=null){
            next=p.next;
            if(head==null){
                head=next;
                newcur=next;
            }else{
                newcur.next=next;
                newcur=newcur.next;
            }
            p=next.next;
        }
        return head;
    }
}
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}