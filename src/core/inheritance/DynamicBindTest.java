package core.inheritance;
public class DynamicBindTest{
    static void dosomething(Shape s){
        System.out.print("in do something ");
        s.draw();
    }
    public static void main(String[] args){
        Shape a=new Shape();
        a.draw();
        dosomething(a);

        Shape b=new Circle();
        b.draw();
        dosomething(b);

        Rectangle c=new Rectangle();
        c.draw();
        dosomething(c);
    }

}
 class Shape{
    static void draw(){
        System.out.println("I am a Shape");
    }
}
 class Circle extends Shape{
    static void draw(){
        System.out.println("I am a Circle");
    }
}
 class Rectangle extends Shape{
    static void draw(){
        System.out.println("I am a rectangle");
    }
}