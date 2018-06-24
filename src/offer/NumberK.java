package offer;

public class NumberK {
    public static void main(String[] args){
        Integer t=0;
        testInteger(t);
        System.out.println(t);
    }
    static public void testInteger(Integer t){
        t=5;
    }
    public int GetNumberOfK(int [] array , int k) {
        int low=getFirstK(array,0,array.length-1,k);
        int high=getLastK(array,0,array.length-1,k);
        if(low==-1&&high==-1)
            return 0;
        else
            return high-low+1;
    }
    public int getFirstK(int[] array,int low,int high,int k){
        int mid=low+(high-low)/2;
        if(array[mid]==k){
            if(mid==0||array[mid-1]!=k)
                return mid;
            else{
                return getFirstK(array,low,mid-1,k);
            }
        }else if(array[mid]<k){
            return getFirstK(array,mid+1,high,k);
        }else{
            return getFirstK(array,low,mid-1,k);
        }
    }
    public int getLastK(int [] array,int low,int high,int k){
        int mid=low+(high-low)/2;
        if(array[mid]==k){
            if(mid==array.length-1||array[mid+1]!=k){
                return mid;
            }else{
                return getLastK(array,mid+1,high,k);
            }
        }else if(array[mid]>k){
            return getLastK(array,low,mid-1,k);
        }else{
            return getLastK(array,mid+1,high,k);
        }
    }
}
