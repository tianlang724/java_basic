package oninetest;
import java.util.Arrays;
import java.util.Scanner;
public class Array {
    public static void main(String[] args) {
                test();
        }

    public static void test(){
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        Engine[]  engines=new Engine[n];
        Work[] works=new Work[m];
        for(int i=0;i<n;i++){
            in.nextLine();
            engines[i]=new Engine(in.nextInt(),in.nextInt());

        }
        for(int i=0;i<m;i++){
            in.nextLine();
            works[i]=new Work(in.nextInt(),in.nextInt());

        }
        Arrays.sort(works);
        Arrays.sort(engines);
        int worknum=0;
        boolean flag[]=new boolean [m];
        Arrays.fill(flag,false);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!flag[j]&&works[i].time<=engines[j].time&&works[i].hard<=engines[j].hard){
                    flag[i]=true;
                    worknum++;
                }
            }
        }
        int value=0;
        for(int i=0;i<m;i++){
            if(flag[i]){
                value+=200*works[i].time+3*works[i].hard;
            }
        }
        System.out.print(worknum+" "+value);
    }

}
class Work implements Comparable{
    int time;
    int hard;
    Work(int time,int hard){
        this.time=time;
        this.hard=hard;
    }

    @Override
    public int compareTo(Object o) {
        Work work=(Work)o;
        if(this.time<work.time){
            return -1;
        }else if(this.time>work.time){
            return 1;
        }else{
            if(this.hard<work.hard){
                return 1;
            }else if(this.hard>work.hard){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
class Engine implements Comparable{
    int time;
    int hard;
    Engine(int time,int hard){
        this.time=time;
        this.hard=hard;
    }

    @Override
    public int compareTo(Object o) {
        Engine work=(Engine)o;
        if(this.time<work.time){
            return -1;
        }else if(this.time>work.time){
            return 1;
        }else{
            if(this.hard<work.hard){
                return 1;
            }else if(this.hard>work.hard){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
