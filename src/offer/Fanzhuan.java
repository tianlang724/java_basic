package offer;

public class Fanzhuan {
    static public void main(String[] args){
        ReverseSentence(" ");
    }
    static public String ReverseSentence(String str) {
        if(str==null)
            return null;
        if(str.equals(" "))
            return " ";
        String[] strs=str.split(" ");
        StringBuilder sb=new StringBuilder();
        for(int i=strs.length-1;i>=0;i++){
            sb.append(strs[i]);
            if(i!=0)
                sb.append(" ");
        }
        return sb.toString();
    }
}
