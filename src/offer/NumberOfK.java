package offer;

public class NumberOfK {
    public static void main(String[] args){
        NumberOfK s=new NumberOfK();
        int[] t={1};
        System.out.println(s.getNumnberOfK(t,1));
    }
    int getNumnberOfK(int[] arr,int k){
        int start=getFirstK(arr,0,arr.length-1,k);
        int end=getLastK(arr,0,arr.length-1,k);
        if(start==-1||end==-1){
            return 0;
        }else if(start>=0&&end>=0) {
            return end - start + 1;
        }else{
            return 1;
        }
    }
    int getFirstK(int[] arr,int low,int high,int k){
        if(low>high){
                return -1;
        }
        int mid=low+(high-low)/2;
        if(arr[mid]==k){
            if(mid>0&&arr[mid-1]!=k||mid==0){
                return mid;
            }else{
                return getFirstK(arr,low,mid-1,k);
            }
        }else if(arr[mid]>k){
            return getFirstK(arr,low,mid-1,k);
        }else{
            return getFirstK(arr,mid+1,high,k);
        }

    }
    int getLastK(int[] arr,int low,int high,int k){
        if(low>high){
            return -1;
        }

        int mid=low+(high-low)/2;
        if(arr[mid]==k){
            if(mid<arr.length-1&&arr[mid+1]!=k||mid==arr.length-1){
                return mid;
            }else{
                return getLastK(arr,mid+1,high,k);
            }
        }else if(arr[mid]>k){
            return getLastK(arr,low,mid-1,k);
        }else{
            return getLastK(arr,mid+1,high,k);
        }

    }
}
