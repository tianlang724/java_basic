package CodingGuide.List;

import CodingGuide.ListNode;

public class SortListPublicPart {
    public static void main(String[] args){

    }

    static public void printPublicPart(ListNode list1,ListNode list2){
        if(list1==null||list2==null){
            return;
        }
        while (list1!=null&&list2!=null){
            if(list1.val>list2.val){
                list2=list2.next;
            }else if(list1.val<list2.val){
                list1=list1.next;
            }else{
                System.out.println(list1.val);
                list1=list1.next;
                list2=list2.next;
            }
        }
    }
}
