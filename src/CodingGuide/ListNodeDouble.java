package CodingGuide;

import java.util.Random;

public class ListNodeDouble {
    public int val;
    public ListNodeDouble pre;
    public ListNodeDouble next;
    public ListNodeDouble(int val){
        this.val=val;
    }
    static public void printList(ListNodeDouble list){
        while (list!=null){
            System.out.print(list.val+" ");
            list=list.next;
        }
        System.out.println();
    }
    static public ListNodeDouble createDoubleList(int k){
        if(k<1){
            return null;
        }
        Random random=new Random();
        ListNodeDouble head=new ListNodeDouble(random.nextInt(500));
        ListNodeDouble pre=head;
        for(int i=0;i<k-1;i++){
            ListNodeDouble node=new ListNodeDouble(random.nextInt(500));
            node.pre=pre;
            pre.next=node;
            pre=node;
        }
        return  head;
    }
}
