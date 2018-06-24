package oninetest.weizhong;

import java.util.Scanner;

import java.util.Scanner;

public class weizhong2 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num = 0;
        for(int i = 2; i <= n ;i++){
            if(isPrime(i)){
                int k = 1;
                while(Math.pow(i, k) <= n){

                    num++;
                    k++;
                }
            }
        }
        System.out.println(num);


    }
    public static boolean isPrime(int n){
        boolean flag = true;
        if(n < 2) return false;
        int i=2;
        while(i <= Math.sqrt(n)){
            if( n % i == 0){
                flag = false;
                break;
            }
            i++;
        }
        return flag;
    }
}