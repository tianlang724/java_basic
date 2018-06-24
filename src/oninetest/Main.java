package oninetest;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String address=scanner.nextLine();
        if ("0000:0000:0000:0000:0000:0000:0000:0000".equalsIgnoreCase(address)){
            System.out.println("Unspecified");
        }else if ("0000:0000:0000:0000:0000:0000:0000:0001".equalsIgnoreCase(address)){
            System.out.println("Loopback");
        }else {
            String[] strings = address.split(":");
            if (strings.length != 8) {
                System.out.println("Error");
            } else {
                for (int i = 0; i < 8; i++) {

                    if (strings[i].length() != 4) {
                        System.out.println("Error");
                        return;
                    }
                }
                if (strings[0].startsWith("FE8") || strings[0].startsWith("FE9") || strings[0].startsWith("FEA") || strings[0].startsWith("FEB")) {
                    System.out.println("LinkLocal");
                    return;
                }
                else if (strings[0].startsWith("FEC") || strings[0].startsWith("FED") || strings[0].startsWith("FEE") || strings[0].startsWith("FEF")) {
                    System.out.println("SiteLocal");
                    return ;
                }

                else if (strings[0].startsWith("FF")) {
                    System.out.println("Multicast");
                    return;
                }

                System.out.println("GlobalUnicast");
            }
        }
    }

    private static String getResult(String address) {
        if ("0000:0000:0000:0000:0000:0000:0000:0000".equalsIgnoreCase(address)){
            return "Unspecified";
        }
        if ("0000:0000:0000:0000:0000:0000:0000:0001".equalsIgnoreCase(address)){
            return "Loopback";
        }
        String[] strings=address.split(":");
        if (strings.length!=8){
            return "Error" ;
        }else {
            for (int i = 0; i < 8; i++) {

                if (strings[i].length() != 4) {
                    return "Error";
                }
            }
            if (strings[0].startsWith("FE8")||strings[0].startsWith("FE9")||strings[0].startsWith("FEA")||strings[0].startsWith("FEB")){
                return "LinkLocal";
            }
            if (strings[0].startsWith("FEC")||strings[0].startsWith("FED")||strings[0].startsWith("FEE")||strings[0].startsWith("FEF")){
                return "SiteLocal";
            }

            if (strings[0].startsWith("FF")) {
                return "Multicast";
            }

            return "GlobalUnicast";
        }
    }
}
