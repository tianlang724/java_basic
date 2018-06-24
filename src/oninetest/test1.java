package oninetest;

import algorithm4.api.In;

import java.util.Scanner;

public class test1 {
   public static void main(String[] args){
       Scanner in = new Scanner(System.in);
       int count= Integer.parseInt(in.nextLine());
       long target=0;
       long[] targets=new long[count];
       for(int i=0;i<count;i++){
           targets[i]= Long.parseLong(in.nextLine());
       }
       for(int i=0;i<count;i++){
           target=targets[i];
           if(target%2==0){
               for(int j=2;j<target/2;j+=2){
                   if(target%j==0){
                       System.out.println(target/j+" "+j);
                       break;
                   }
               }
               if(i>target/2)
                   System.out.println("No");
           }else{
               System.out.println("No");
           }
       }
   }
   public void tt(){
       Scanner in = new Scanner(System.in);
       int t = in.nextInt();
       long[] nums = new long[t];
       for (int i = 0; i < t; i++) {
           nums[i] = in.nextLong();
       }
       for (int i = 0; i < t; i++) {
           boolean has = false;
           long N = nums[i];
           for (long y = 2; y <= N / 2 && !has; y *= 2) {
               long x = N / y;
               if (x % 2 != 0 && x * y == N) {
                   System.out.println(x + " " + y);
                   has = true;
               }
           }
           if (!has) {
               System.out.println("No");
           }
       }
   }

}

