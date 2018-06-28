package CodingGuide.List;

import CodingGuide.ListNode;
import thinking.net.mindview.simple.List;

public class QuickSortPartList {
    static public void main(String[] args){
        ListNode list=ListNode.createList(5);
        ListNode.printList(list);
        ListNode ret=quickSort(list,250);
        ListNode.printList(ret);
    }
    static public ListNode quickSort(ListNode list,int k){
        if(list==null||list.next==null)
            return list;
        ListNode p=list;
        ListNode firstHead=null,firstEnd=null;
        ListNode secondHead=null,secondEnd=null;
        ListNode thirdHead=null,thirdEnd=null;
        while (p!=null){
            if(p.val<k){
                if(firstHead==null){
                    firstHead=p;
                    firstEnd=p;
                }else{
                    firstEnd.next=p;
                    firstEnd=firstEnd.next;
                }
            }else if(p.val>k){
                if(thirdHead==null){
                    thirdHead=p;
                    thirdEnd=p;

                }else{
                    thirdEnd.next=p;
                    thirdEnd=thirdEnd.next;
                }
            }else{
                if(secondHead==null){
                    secondHead=p;
                    secondEnd=p;

                }else{
                    secondEnd.next=p;
                    secondEnd=secondEnd.next;
                }
            }
            p=p.next;
        }
        if(firstEnd!=null){
            firstEnd.next=(secondHead==null?thirdHead:secondHead);
            if(secondEnd!=null){
                secondEnd.next=thirdHead;
            }
            if(thirdHead!=null)
                thirdEnd.next=null;

            return firstHead;
        }else if(secondHead!=null){
            secondEnd.next=thirdHead;
            if(thirdHead!=null)
                thirdEnd.next=null;
            return secondHead;
        }else{
            thirdEnd.next=null;
            return thirdHead;
        }
    }
}
