package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class MaxSubMatrix {
    static public void main(String[] args){
        int[][] matrix={{1,0,1,1},{1,1,1,0},{1,1,1,1}};
        System.out.println(maxSubMatrix(matrix));

    }
    static public int maxSubMatrix(int[][] matrix){
        int maxSubStrix=0;
        int rows=matrix.length;
        int cols=matrix[0].length;
        int[] arr=new int[cols];
        LinkedList<Integer> stack=new LinkedList<>();
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                arr[i]+=matrix[j][i];
            }
        }
        for(int i=0;i<cols;i++){
            while(!stack.isEmpty()&&arr[stack.peek()]>=arr[i]){
                 int temp=stack.pop();
                 int k=stack.isEmpty()?-1:stack.peek();
                 int tmax=(i-k-1)*arr[temp];
                 if(tmax>maxSubStrix)
                     maxSubStrix=tmax;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int temp=stack.pop();
            int k=stack.isEmpty()?-1:stack.peek();
            int tmax=(cols-k-1)*arr[temp];
            if(tmax>maxSubStrix)
                maxSubStrix=tmax;
        }

        return maxSubStrix;
    }
}
