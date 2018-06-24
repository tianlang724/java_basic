package offer;

import java.util.ArrayList;
import java.util.Collections;

public class string_pailie {
    static  public void main(String[] args) {
        System.out.println(Permutation("a"));
    }
    static char[] strArr;
    static ArrayList<String> ret=new ArrayList<>();
    static public ArrayList<String> Permutation(String str) {
        if(str.length()==0)
            return ret;
        strArr=str.toCharArray();
//        strArr=new char[str.length()+1];
//        for(int i=0;i<str.length();i++){
//            strArr[i]=str.charAt(i);
//        }
//        strArr[str.length()]='\0';
        Per(0);
        Collections.sort(ret);
        return ret;
    }
    static public void Per(int i){
        if(i==strArr.length-1){
            String s=new String(strArr);
            ret.add(s);

        }
        if(i+1<strArr.length){
            Per(i+1);
        }
        for(int j=i+1;j<strArr.length;j++){
            if(strArr[i]!=strArr[j]){
                char t=strArr[i];
                strArr[i]=strArr[j];
                strArr[j]=t;
                Per(i+1);
                t=strArr[i];
                strArr[i]=strArr[j];
                strArr[j]=t;
            }
        }
    }



}