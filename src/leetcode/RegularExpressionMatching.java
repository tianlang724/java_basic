package leetcode;

public class RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        if(p.length()==0)
            return s.length()==0;
        int lens=s.length();
        int lenp=p.length();
        boolean[][] dp=new boolean[lens+1][lenp+1];
        //初始化
        dp[0][0]=true;
        for(int i=0;i<lenp;i++){
            if(p.charAt(i)=='*'&&dp[0][i-1])
                dp[0][i+1]=true;
        }
        for(int i=0;i<lens;i++){
            for(int j=0;j<lenp;j++){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='.'){
                    dp[i+1][j+1]=dp[i][j];
                }else if(p.charAt(j)=='*'){
                    if(p.charAt(j-1)!=s.charAt(i)&&p.charAt(j-1)!='?'){
                        dp[i+1][j+1]=dp[i+1][j-1];
                    }else{
                        dp[i+1][j+1]=dp[i+1][j-1]||dp[i+1][j]||dp[i][j+1];
                    }
                }
            }
        }
        return dp[lens][lenp];
    }
    public static void main(String[] args){
        System.out.println(isMatch("aa",".*"));
    }
}
