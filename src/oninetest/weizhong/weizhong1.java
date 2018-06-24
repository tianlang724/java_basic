package oninetest.weizhong;
import java.util.Scanner;

public class weizhong1 {
    static int cur;
    static long temp=1;
    public static  void main(String[] arg){
        Scanner in=new Scanner(System.in);
        Long n=in.nextLong();
        for (int i = 60; i >= 0; -- i) {
            if ((n >>i & 1)>0) {
                cur = i;
                break;
            }
        }
        long ret = 1;
        for (int i = 1; i <= cur; ++ i) {
            int x = i - 2;
            if (x > 0) {
                ret += temp<<((x + 1)/2);
            }
            else {
                ++ret;
            }
        }
        for (int i = cur - 1, j = 1; i >= j; ) {
            if ((n >> i & 1)>0) {
                int x = i - j - 1;
                if (x > 0) {
                    ret += temp<<((x + 1)/2);
                }
                else
                    ++ ret;
            }
            -- i;
            ++ j;
        }
        long m = 0;
        int i = cur;
        int j=0;
        while ( i >= j) {
            if ((n >> i & 1)>0) {
                m |= temp<< j;
                m |= temp<< i;

            }
            -- i;
            ++ j;
        }
        if (m <= n)
            ++ ret;
        System.out.println(ret);

    }

}