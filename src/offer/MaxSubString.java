package offer;

import algorithm4.api.In;

import java.util.*;

public class MaxSubString {
    static public  void main(String[] args){
        Scanner in=new Scanner(System.in);
        String s="arabcacf";
        System.out.println(getMaxSubString(s));
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(getMaxSubStringDP(s));
        System.out.println(GetUglyNumber_Solution(4));
    }
    static public int GetUglyNumber_Solution(int index) {
        if(index<=0)
            return 0;
        int[] cache=new int[index];
        cache[0]=1;
        int index2=0;
        int index3=0;
        int index5=0;
        int i=1;
        while(i<index){
            cache[i]=Math.min(cache[index2]*2,Math.min(cache[index2]*3,cache[index5]*5));
            if(cache[i]==cache[index2]*2) index2++;
            if(cache[i]==cache[index3]*3) index3++;
            if(cache[i]==cache[index5]*5) index5++;
            i++;
        }
        return cache[index-1];
    }
    public static int getMaxSubString(String s){
        if(s==null||s.length()==0)
            return -1;
        if(s.length()==1)
            return 1;
        HashMap<Character,Integer> map=new HashMap<>();
        int maxLen= Integer.MIN_VALUE;
        char[] chars=s.toCharArray();
        int len=0;
        for(int i=0;i<chars.length;i++){
            if(map.containsKey(chars[i])){
                if(len>maxLen)
                    maxLen=len;
                i=map.get(chars[i]);
                map.clear();
                len=0;
            }else{
                len++;
                map.put(chars[i],i);
            }
        }
        if(len>maxLen)
            maxLen=len;
        return maxLen;
    }
    static public int lengthOfLongestSubstring(String str) {
        int cur=0;
        int end=str.length();
        int max_length=0;
        int length=0;
        char[] s=str.toCharArray();
        boolean[] exist=new boolean[256];
        while(cur<end){
            if(!exist[s[cur]]){
                exist[s[cur]]=true;
                cur++;
                length++;
            }else{
                if(length>max_length)
                    max_length=length;
                cur=cur-length+1;
                length=0;
                Arrays.fill(exist,false);
            }
        }
        if(length>max_length)
            max_length=length;
        return max_length;

    }
    public static int getMaxSubStringDP(String s){
        if(s==null||s.length()==0)
            return -1;
        if(s.length()==1)
            return 1;
        HashMap<Character,Integer> map=new HashMap<>();
        char[] chars=s.toCharArray();
        int[] f=new int[chars.length+1];
        f[0]=0;
        for(int i=0;i<chars.length;i++){
            if(map.containsKey(chars[i])){
                int t=i-map.get(chars[i]);
                if(t<=f[i]){
                    f[i+1]=t;
                }else{
                    f[i+1]=f[i]+1;
                }
                map.put(chars[i],i);
            }else{
                f[i+1]=f[i]+1;
                map.put(chars[i],i);
            }
        }
        int maxLen=f[0];
        for(int i=0;i<=chars.length;i++){
            if(f[i]>maxLen)
                maxLen=f[i];
        }
        return maxLen;
    }

}
