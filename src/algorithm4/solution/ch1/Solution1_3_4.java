package algorithm4.solution.ch1;

import algorithm4.api.StdOut;
import algorithm4.basic.Stack;

public class Solution1_3_4 {
    public static void main(String[] args){
        Character c;
        boolean flag=true;
        Stack<Character> op=new Stack<Character>();
        Character last;
        String pstring="[()]{[()()]()}";
        int len=pstring.length();
        for(int i=0;i<len;i++){
            c=pstring.charAt(i);
            switch (c){
                case ']':
                    last=op.peek();
                    if(!last.equals('['))
                        flag = false;
                    else
                        op.pop();
                    break;
                case '}': last=op.peek();
                    if(!last.equals('{'))
                        flag = false;
                    else
                        op.pop();
                    break;
                case ')':
                    last=op.peek();
                    if(!last.equals('('))
                        flag = false;
                    else
                        op.pop();
                    break;
                default:
                    op.push(c);
                    break;
            }
            if(flag==false)
                break;
        }
        StdOut.println(flag);
    }
}
