package offer;

public class IsNumber {
    static public void main(String[] args){
        IsNumber test=new IsNumber();
        String s="12e";
        System.out.println(test.isNumeric(s.toCharArray()));
    }
    public boolean isNumeric(char[] str) {
        if(str==null||str.length==0)
            return false;
        boolean hasSign=false;
        boolean hasSign2=false;
        boolean isE=false;
        boolean isDec=false;
        int num1=0;
        int num2=0;
        int num3=0;
        int i=0;
        if(str[i]=='-'||str[i]=='+'){
            hasSign=true;
            i++;
        }
        while(i<str.length&&str[i]<='9'&&str[i]>='0'){
            i++;
            num1++;
        }
        if(i==str.length){
            if(num1>0)
                return true;
            return false;
        }else if(str[i]=='.'){
            isDec=true;
            i++;
        }else  if(str[i]=='E'||str[i]=='e'){
            isE=true;
            i++;
        }else{
            return false;
        }
        if(i<str.length&&str[i]=='+'||str[i]=='-'){
            i++;
            hasSign2=true;
        }
        while(i<str.length&&str[i]<='9'&&str[i]>='0'){
            i++;
            num2++;
        }
        if(i==str.length){
            if(isDec){
                if((num1>0||num2>0)&&!hasSign2){
                    return true;
                }
                return false;
            }else if(isE){
                if(num1>0&&num2>0)
                    return true;
                return false;
            }
        }
        if(str[i]=='E'||str[i]=='e'){
            isE=true;
            i++;
        }
        if(i<str.length&&str[i]=='+'||str[i]=='-'){
            i++;
        }
        while(i<str.length&&str[i]<='9'&&str[i]>='0'){
            i++;
            num3++;
        }
        if(i==str.length){
            if(isE&&num1>0&&num2>0&&num3>0)
                return true;
            return false;
        }
        return false;




    }
}
