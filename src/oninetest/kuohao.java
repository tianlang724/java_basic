package oninetest;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kuohao {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int count= Integer.parseInt(in.nextLine());
        String target;
        for(int i=0;i<count;i++){
            target=in.nextLine();
            char[] chars=target.toCharArray();
            boolean flag=false;
            int num=0;
            boolean find=false;
            int fu=0;
            for(int j=0;j<chars.length;j++){
                if(chars[j]=='('){
                    num++;
                }
                else if(chars[j]==')'){
                    num--;
                    if(num<0){
                        fu++;
                    }
                }
            }
            if(fu<=1&&num==0){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }

        }

    }
    public static void swap(String target){
        char[] chars=target.toCharArray();
        for(int j=0;j<target.length();j++){
            for(int k=j+1;k<target.length();k++){
                if(chars[j]!=chars[k]){
                    char ch=chars[j];
                    chars[j]=chars[k];
                    chars[k]=ch;
                    if(matching(chars)){
                        System.out.println("Yes");
                        return ;
                    }
                    ch=chars[j];
                    chars[j]=chars[k];
                    chars[k]=ch;

                }
            }
        }
        System.out.println("No");
    }
    public static boolean matching(char[] chs) {
        int count = 0;
        for (char c : chs) {
            if (count < 0 || !(c == '(' || c == ')')) {
                return false;
            } else if (c == '(') {
                count++;
            } else {
                count--;
            }
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }
}
