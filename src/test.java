import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class test {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5,6,7};
        //LinkedList
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int rows=matrix.length;
        int cols=matrix.length;
        ArrayList<Integer> ret=new ArrayList<>();
        int k=rows/2;
        for(int i=0;i<=k;i++){
            for(int j=0+i;j<cols-i;j++){
                ret.add(matrix[i][j]);
            }
            for(int j=i+1;j<rows-i;j++){
                ret.add(matrix[j][cols-i-1]);
            }
            for(int j=cols-i-2;j>=i;j--){
                ret.add(matrix[rows-i-1][j]);
            }
            for(int j=rows-i-2;j>i;j--){
                ret.add(matrix[j][i]);
            }
        }
        return ret;
    }
}


