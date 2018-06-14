package leetcode;

import java.util.ArrayList;
import java.util.List;

public class IntToRoman {
//    public static String intToRoman(int num) {
//        String[][] nums={{"","I","II","III","IV","V","VI","VII","VIII","IX"},{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},                                           {"","C","CC","CCC","CD","D","DC","DCC","DCCC","DM"},{"","M","MM","MMM"}};
//        StringBuilder sb=new StringBuilder();
//        int i=0;
//        while(num!=0){
//            int temp=num%10;
//            sb.append(nums[i][temp]);
//            num/=10;
//            i++;
//        }
//        return sb.reverse().toString();
//
//    }
public static String intToRoman(int num) {
    String[][] nums={{"","I","II","III","IV","V","VI","VII","VIII","IX"},{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},                                           {"","C","CC","CCC","CD","D","DC","DCC","DCCC","DM"},{"","M","MM","MMM"}};
    List<String> list=new ArrayList<>();
    int i=0;
    while(num!=0){
        int temp=num%10;
        list.add(nums[i][temp]);
        num/=10;
        i++;
    }
    StringBuilder sb=new StringBuilder();
    for(int j=list.size()-1;j>=0;j--){
        sb.append(list.get(j));
    }
    return sb.toString();

}
    public static void main(String[] args){
        System.out.println(intToRoman(4));
    }
}
