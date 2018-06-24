package offer;

public class nixudui {
    public static void main(String[] args){
        nixudui ni=new nixudui();
        int[] test={1,2,3,4,5,6,7,0};
        System.out.println(ni.InversePairs(test));
    }
    int ret=0;
    int[] temp;
    final int mod=1000000007;
    public int InversePairs(int [] array) {
        temp=new int[array.length];
        sort(array,0,array.length-1);
        return ret;
    }
    public void sort(int[] arr,int low,int high){
        if(low>=high)
            return;
        int mid=low+(high-low)/2;
        sort(arr,low,mid);
        sort(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    public void merge(int[] arr,int low,int mid,int high){
        int i=low;
        int j=mid+1;
        int k=low;
        while(i<=mid||j<=high){
            if(i>mid){
                temp[k++]=arr[j++];
            }else if(j>high){
                temp[k++]=arr[i++];
            }else if(arr[i]>arr[j]){
                temp[k++]=arr[i++];
                ret+=high-j+1;
                ret%=mod;
            }else{
                temp[k++]=arr[j++];
            }
        }
        for(k=low;k<=high;k++){
            arr[k]=temp[k];
        }
    }
}
