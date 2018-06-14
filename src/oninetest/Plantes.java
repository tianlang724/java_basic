package oninetest;

public class Plantes {
    public static void main(String[] args){
        int[] temp={7,5,6,3,5,4,8,2,1};
        System.out.print(plan(8,temp));
        String s1="abc"+"def";
        String s2=new String(s1);
        if(s1==s2)
            System.out.println(1);
        if(s1.equals(s2))
            System.out.println(2);
    }

    public static int plan(int num,int[] height){
        int left=0;
        int k=0;
        boolean flag=false;
        while(true){
            int i=0;
            flag=false;
            while(i<height.length&&height[i]==0){
                i++;
            }
            left=height[i];
            for(int j=i+1;j<height.length;j++){
                if(height[j]!=0&&height[j]>left){
                    left=height[j];
                    flag=true;
                    height[j]=0;
                }else if(height[j]!=0) {
                    left = height[j];
                }
            }
            if(!flag)
                break;
            else
                k++;
        }
        return k;
    }
}
