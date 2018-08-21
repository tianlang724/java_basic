package CodingGuide.List;

import CodingGuide.ListNode;
import org.w3c.dom.ls.LSException;
import thinking.net.mindview.simple.List;

public class ListIntersect {
    public static void main(String[] args){
        // 都无环测试
//        ListNode p1=ListNode.createList(3);
//        ListNode p2=ListNode.createList(5);
//        ListNode p3=ListNode.createList(1);
//        ListNode p=p2;
//        while (p!=null){
//            if(p.next==null) break;
//            p=p.next;
//        }
//        p.next=p1;
//        p=p3;
//        while (p!=null){
//            if(p.next==null) break;
//            p=p.next;
//        }
//        p.next=p1;
//        ListNode.printList(p2);
//        ListNode.printList(p3);
//        System.out.println(isListIntersect(p2,p3).val);

        ListNode p0= ListNode.createList(2);
        ListNode p1=ListNode.createListRing(3);
        ListNode p2=ListNode.createList(5);
        ListNode p3=ListNode.createList(2);
        ListNode.printList(p0);
        ListNode.printList(p2);
        ListNode.printList(p3);
        System.out.println(p1.val);
        ListNode p=p2;
        while (p!=null){
            if(p.next==null) break;
            p=p.next;
        }
        p.next=p0;
        while (p!=null){
            if(p.next==null) break;
            p=p.next;
        }
        p.next=p1;
        p=p3;
        while (p!=null){
            if(p.next==null) break;
            p=p.next;
        }
        p.next=p0;
        System.out.println(isListIntersect(p2,p3).val);
    }
    static public ListNode isListIntersect(ListNode list1, ListNode list2){
        ListNode ring1=isListRing(list1);
        ListNode ring2=isListRing(list2);
        if(ring1==null&&ring2==null){
            return isIntersectNoRing(list1,list2);
        }else if(ring1!=null&&ring2!=null){
            if(ring1==ring2){
                ListNode ret=isIntersectNoRing(list1,list2,ring1);
                if(ret==null)
                    return ring1;
                return ret;
            }else {

                ListNode p1 = ring1;
                do {
                    p1 = p1.next;
                } while (p1 != ring1 && p1 != ring2);
                if (p1 == ring1)
                    return null;
                return p1;
            }

        }else{
            return null;
        }

    }
    static private ListNode isListRing(ListNode list){
        if(list==null||list.next==null)
            return null;
        ListNode pQuick=list;
        ListNode pSlow=list;
        do{
            if(pQuick.next==null)
                break;
            pQuick=pQuick.next.next;
            pSlow=pSlow.next;

        }while (pQuick!=null&&pQuick!=pSlow);
        if(pQuick!=pSlow)
            return null;
        pQuick=list;
        while (pQuick!=pSlow){
            pQuick=pQuick.next;
            pSlow=pSlow.next;
        }
        return pSlow;
    }

    static private ListNode isIntersectNoRing(ListNode list1,ListNode list2){
        if(list1==null||list2==null)
            return null;
        ListNode p1=list1;
        ListNode p2=list2;
        int num1=0;
        int num2=0;
        while (p1!=null){
            num1++;
            p1=p1.next;
        }
        while (p2!=null){
            num2++;
            p2=p2.next;
        }
        //p1 为长的 p2为短的
        p1=num1>num2?list1:list2;
        p2=num1>num2?list2:list1;
        num1=Math.abs(num1-num2);
        num2=0;
        while (num2<num1){
            p1=p1.next;
            num2++;
        }
        while (p1!=null&&p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        if(p1==null)
            return null;
        return p1;
    }

    static private ListNode isIntersectNoRing(ListNode list1,ListNode list2,ListNode end){
        if(list1==null||list2==null)
            return null;
        ListNode p1=list1;
        ListNode p2=list2;
        int num1=0;
        int num2=0;
        while (p1!=end){
            num1++;
            p1=p1.next;
        }
        while (p2!=end){
            num2++;
            p2=p2.next;
        }
        //p1 为长的 p2为短的
        p1=num1>num2?list1:list2;
        p2=num1>num2?list2:list1;
        num1=Math.abs(num1-num2);
        num2=0;
        while (num2<num1){
            p1=p1.next;
            num2++;
        }
        while (p1!=end&&p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        if(p1==end)
            return null;
        return p1;
    }

}
