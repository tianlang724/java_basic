package CodingGuide.List;

import CodingGuide.ListNode;

public class InsertNodeInSortRing {
    static public void main(String[] args){
        ListNode ring=ListNode.createListRing(5);
       ring=insertNodeInSortRing(ring,0);
        ring=insertNodeInSortRing(ring,5);
        ring=insertNodeInSortRing(ring,20);
        System.out.println();
    }
    static public ListNode insertNodeInSortRing(ListNode list,int num){
        ListNode node=new ListNode(num);
        if(list==null){
            node.next=node;
            return node;
        }
        ListNode p=list;
        ListNode pre=null;

        do{
            if(num<p.val){
                node.next=p;
                if(pre==null){
                    while (p.next!=list){
                        p=p.next;
                    }
                    p.next=node;
                    return node;
                }else{
                    pre.next=node;
                    return list;
                }
            }
            pre=p;
            p=p.next;
        }while (p!=list);
        pre.next=node;
        node.next=list;
        return list;
    }
}
