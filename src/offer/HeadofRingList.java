package offer;

import java.util.Comparator;
import java.util.HashSet;

public class HeadofRingList {
    public static void main(String[] args){
        ListNode head=new ListNode(1);
        ListNode node=new HeadofRingList().EntryNodeOfLoop(head);
        if(node!=null){
            System.out.println(node.val);
        }
    }
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead==null)
            return null;
        HashSet<ListNode> set=new HashSet<>();
        while(pHead!=null){
            if(set.contains(pHead)){
                return pHead;
            }else{
                set.add(pHead);
            }
        }
        return null;
    }

}
class ListNode implements Comparator {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}

