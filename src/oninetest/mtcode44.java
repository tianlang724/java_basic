package oninetest;



import java.util.*;

public class mtcode44 {
    static public void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        int C=in.nextInt();
        double[] rate=new double[m];
        for(int i=0;i<m;i++){
            rate[i]=in.nextInt();
        }
        boolean flag=false;
        int nodatalie=0;
        int nodatahang=0;
        int[][] stu=new int[n][m];
        int[] maxgoal=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                stu[i][j]=in.nextInt();
                if(stu[i][j]==-1){
                    nodatalie=j;
                    nodatahang=i;
                }
            }
        }
        if(k==n){
            for(int i=0;i<n;i++)
                System.out.println(1);
            return;
        }
        ArrayList<Node> nodes=new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(maxgoal[i]< stu[j][i]){
                    maxgoal[i]=stu[j][i];
                }
            }
        }

        stu[nodatahang][nodatalie]=0;
        for(int i=0;i<n;i++){
            double num=0;
            for(int j=0;j<m;j++){
                if(maxgoal[j]!=0){
                    num+=((double)stu[i][j])/maxgoal[j]*rate[j];
                }
            }
            nodes.add(new Node(i,num));
        }
        stu[nodatahang][nodatalie]=C;
        if(maxgoal[nodatalie]<C)
            maxgoal[nodatalie]=C;
        for(int i=0;i<n;i++){
            double num=0;
            for(int j=0;j<m;j++){
                if(maxgoal[j]!=0){
                    num+=((double)stu[i][j])/maxgoal[j]*rate[j];
                }
            }
            nodes.get(i).goal2=num;
        }
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o2.goal1>o1.goal1)
                    return 1;
                else if(o2.goal1<o1.goal1)
                    return -1;
                return 0;
            }
        });
        double yuzhi=nodes.get(k-1).goal1;
        flag=false;
        if(nodes.get(k).goal1==yuzhi)
            flag=true;
        for(int i=0;i<nodes.size();i++){
            Node node=nodes.get(i);
            if(node.goal1>yuzhi)
                node.flag1=1;
            else if(node.goal1<yuzhi)
                node.flag1=2;
            else{
                if(flag){
                    node.flag1=3;
                }else{
                    node.flag1=1;
                }
            }
        }
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o2.goal2>o1.goal2)
                    return 1;
                else if(o2.goal2<o1.goal2)
                    return -1;
                return 0;
            }
        });
        yuzhi=nodes.get(k-1).goal2;
        flag=false;
        if(nodes.get(k).goal2==yuzhi)
            flag=true;
        for(int i=0;i<nodes.size();i++){
            Node node=nodes.get(i);
            if(node.goal2>yuzhi)
                node.flag2=1;
            else if(node.goal2<yuzhi)
                node.flag2=2;
            else{
                if(flag){
                    node.flag2=3;
                }else{
                    node.flag2=1;
                }
            }
        }
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.num-o2.num);
            }
        });
        for(int i=0;i<nodes.size();i++){
            Node node=nodes.get(i);
            if(node.flag1==node.flag2)
                System.out.println(node.flag1);
            else
                System.out.println(3);
        }
    }


}
class Node{
    int num;
    double goal1;
    double goal2;
    int flag1;
    int flag2;
    public Node(int i, double tempsum) {
        this.num=i;
        this.goal1=tempsum;
    }
}



