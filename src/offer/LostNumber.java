package offer;

public class LostNumber {
    static public void main(String[] args){
        int[] t={0,1,2,3,5,6};
        System.out.println(getLostNumber(t,0,t.length-1));
        System.out.println(getLostNumberCir(t));
    }
    static public int getLostNumber(int[] arr,int low,int high){
        if(low>high)
            return -1;
        int mid=low+(high-low)/2;
        if(arr[mid]==mid){
            return getLostNumber(arr,mid+1,high);
        }else if(arr[mid]<mid){
            return getLostNumber(arr,low,mid-1);
        }else{
            if(mid>0&&arr[mid-1]==mid-1||mid==0){
                return mid;
            }else{
                return getLostNumber(arr,low,mid-1);
            }
        }
    }
    static public int getLostNumberCir(int[] arr){
        if(arr==null|arr.length==0)
            return -1;
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]==mid){
                low=mid+1;
            }else if(arr[mid]<mid){
                high=mid-1;
            }else{
                if(mid>0&&arr[mid-1]==mid-1||mid==0)
                    return mid;
                else
                    high=mid-1;
            }
        }
        return -1;
    }
}
