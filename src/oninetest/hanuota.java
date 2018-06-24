package oninetest;

public class hanuota {
    public static void main(String[] args){
        process("left","right",2);

    }
    public static void process(String from,String to,int n){
        if(n<1)
            return;
        if(from.equals("mid")||to.equals("mid")){
            String another=from.equals("mid")?(to.equals("left")?"right":"left"):(from.equals("left")?"right":"left");
            process(from,another,n-1);
            System.out.println("move "+n+" from "+from+" to "+to);
            process(another,to,n-1);
        }else{
            String another="mid";
            process(from,another,n);
            process(another,from,n-1);
            System.out.println("move "+n+" from "+another+" to "+to);
            process(from,to,n-1);
        }
    }
}
