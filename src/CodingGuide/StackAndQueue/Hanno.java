package CodingGuide.StackAndQueue;

import algorithm4.api.In;

import java.util.LinkedList;

public class Hanno {
    static public void main(String[] args){
        System.out.println("Recursion: " +getMethondRecursion('A','C',2));
        System.out.println("---------------------------------------------------");
        System.out.println("Recursion: " +getMethondStack(2));


    }


    static char[] hanno={'A','B','C'};
    static  public int getMethondRecursion(char from,char to,int n){
        if(n<=0)
            return 0;
        char mid='0';
        for(int i=0;i<3;i++){
            if(hanno[i]!=from&&hanno[i]!=to)
                mid=hanno[i];
        }
        int ret=0;
        if(from=='B'||to=='B'){
            ret+=getMethondRecursion(from,mid,n-1);
            ret++;
            System.out.println("Move "+n+" from "+from+" to "+to);
            ret+=getMethondRecursion(mid,to,n-1);
        }else{
            ret+=getMethondRecursion(from,to,n-1);
            ret++;
            System.out.println("Move "+n+" from "+from+" to "+mid);
            ret+=getMethondRecursion(to,from,n-1);
            ret++;
            System.out.println("Move "+n+" from "+mid+" to "+to);
            ret+=getMethondRecursion(to,from,n-1);

        }
        return ret;
    }
    static public int getMethondStack(int num){
        LinkedList<Integer> leftStack=new LinkedList<>();
        LinkedList<Integer> midStack=new LinkedList<>();
        LinkedList<Integer> rightStack=new LinkedList<>();
        //初始化
        leftStack.push(Integer.MAX_VALUE);
        midStack.push(Integer.MAX_VALUE);
        rightStack.push(Integer.MAX_VALUE);
        for(int i=num;i>0;i--){
            leftStack.push(i);
        }
        Action[] action={Action.No};
        int step=0;
        while(rightStack.size()<num+1){
            step+=moveBetwennStack(action,Action.MtoL,Action.LtoM,leftStack,midStack,"A","B");
            step+=moveBetwennStack(action,Action.RtoM,Action.MtoR,midStack,rightStack,"B","C");
            step+=moveBetwennStack(action,Action.LtoM,Action.MtoL,midStack,leftStack,"B","A");
            step+=moveBetwennStack(action,Action.MtoR,Action.RtoM,rightStack,midStack,"C","B");

        }
        return step;
    }
    static private int moveBetwennStack(Action[] record,Action preNoAct,Action nowAct,
                                        LinkedList<Integer> fromStack,LinkedList<Integer> toStack,
                                        String from,String to){
        if(record[0]!=preNoAct&&fromStack.peek()<toStack.peek()){
            toStack.push(fromStack.pop());
            System.out.println("Move "+toStack.peek()+" from "+from+" to "+to);
            record[0]=nowAct;
            return 1;
        }
        return 0;
    }

}
enum Action{
    No,LtoM,MtoL,MtoR,RtoM
}
