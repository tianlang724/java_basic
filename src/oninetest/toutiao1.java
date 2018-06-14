package oninetest;

import java.util.Scanner;

public class toutiao1 {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        boolean yinhao=false;
        int  ret=0;
        boolean xiegang=false;
        boolean zhushi2=false;
        String s;
        while(in.hasNextLine()){
            s=in.nextLine();
            char[] ch=s.toCharArray();
            int len=ch.length;
            for(int i=0;i<len;i++){
                if(!zhushi2){
                    if(ch[i]=='"'&&!yinhao)
                        yinhao=true;
                    else if(ch[i]=='"'&&yinhao)
                        yinhao=false;
                    if(!yinhao){
                        if(xiegang){
                            if(ch[i]=='/'){
                                if(i+1<len) {
                                    ret++;
                                    xiegang=false;
                                    break;
                                }
                            }else if(ch[i]=='*'){

                                zhushi2=true;
                            }
                            xiegang=false;
                        }else if(ch[i]=='/') {
                            xiegang = true;
                        }
                    }
                }else if(ch[i]=='*'){
                    if(i+1<len&&ch[i+1]=='/'){
                        zhushi2=false;
                        ret++;
                    }

                }

            }
        }
        System.out.println(ret);
    }
}
