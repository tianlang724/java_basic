package leetcode;

import java.util.Scanner;

public class LongestPalindrom {
    static public void main(String[] args){
        System.out.print(Long());
    }
    static public int Long(){
        Scanner in = new Scanner(System.in);
        String s=in.next();
        if(s.length()<=1) return s.length();
        int len=s.length();
        char[] a=s.toCharArray();
        int max=0;
        for(int i=0;i<len;i++){
            int temp=i/2;
            int j=i/2;
            if(i%2==0)
                j++;
            while(j<len&&a[temp]==a[j]){
                temp--;
                j++;
            }
            if(j-1-temp>max){
                max=j-1-temp;
            }
        }
        return max;

    }
}
