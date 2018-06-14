package offer;

public class GiftMaxValue {
    static public void main(String[] args){
        int[][] gifts={{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};
        getMaxVlaue(gifts,0,0,0);
        System.out.println(maxValue);
        System.out.println(getMaxValueDP(gifts));
    }
    static int maxValue=Integer.MIN_VALUE;
    public static void getMaxVlaue(int[][] gifts,int x,int y,int value){
        int rows=gifts.length;
        int cols=gifts[0].length;
        if(x>=rows||y>=cols)
            return;
        if(x==rows-1&&y==cols-1){
            if(value+gifts[x][y]>maxValue){
                maxValue=value+gifts[x][y];
            }
        }
        value+=gifts[x][y];
        if(x+1<rows){
            getMaxVlaue(gifts,x+1,y,value);
        }
        if(y+1<cols){
            getMaxVlaue(gifts,x,y+1,value);
        }
    }
    public static int getMaxValueDP(int[][] gifts){
        int rows=gifts.length;
        int cols=gifts[0].length;
        int[][] f=new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int t1=0,t2=0;
                if(i-1>=0)
                    t1=f[i-1][j];
                if(j-1>=0)
                    t2=f[i][j-1];
                f[i][j]=Math.max(t1,t2)+gifts[i][j];
            }
        }
        return f[rows-1][cols-1];

    }
}
