package offer;

public class Solution {
    public static void main(String[] args){
        Solution s=new Solution();
        int[] t={1,2,3,4,5,6,7,0};
        System.out.println(s.InversePairs(t));
    }
    public int ret=0;
    int [] temp;
    final int MOD=1000000007;
    public int InversePairs(int [] array) {
        temp=new int[array.length];
        sort(array,0,array.length-1);
        return ret;
    }
    public void sort(int[] array,int low,int high){
        if(low>=high){
            return;
        }
        int mid=low+(high-low)/2;
        sort(array,low,mid);
        sort(array,mid+1,high);
        merge(array,low,mid,high);
    }
    public void merge(int[] array,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        int k=low;
        while(i<=mid||j<=high){
            if(i>mid){
                temp[k++]=array[j++];
            }else if(j>high){
                temp[k++]=array[i++];
            }else if(array[i]>=array[j]){
                temp[k++]=array[i++];
                ret+=high-j+1;
                ret%=MOD;
            }else{
                temp[k++]=array[j++];
            }
        }
        for(k=i;k<=high;k++){
            array[k]=temp[k];
        }
    }
}
