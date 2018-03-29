package core.innerclass.anonymousinnerclass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class AnonymousInnerClassTest{
    public static void main(String[] args){
        TalkingClock clock=new TalkingClock();
        clock.start(1000,true);
        JOptionPane.showMessageDialog(null,"Quit Game?");
        System.exit(0);
    }

}
class TalkingClock{
    public void start(int interval,boolean beep){
        ActionListener listener=new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("The time is "+new Date());
                if(beep) Toolkit.getDefaultToolkit().beep();
            }
        };
        Timer t=new Timer(interval,listener);
        t.start();
    }
}