package core.lambda;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class LambdaTest{
    public static void main(String[] args){
        String[] planets=new String[]{"Mercury","Venus","Earth","Mars","Jupiter","Saturn"};
        System.out.println(Arrays.toString(planets));
        System.out.println("sort in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("sort in length:");
        Arrays.sort(planets,(first,second)->first.length()-second.length());
        System.out.println(Arrays.toString(planets));
        Timer t=new Timer(1000,event->System.out.println("The time is"+new Date()));
        t.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);

    }
}