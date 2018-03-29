package algorithm4.solution.ch1;

public class Rational {
    private int numerator;
    private int denomitor;
    public  Rational(int numerator,int denominator)
    {
        this.numerator=numerator;
        this.denomitor=denominator;
    }
    public Rational plus(Rational b){
        return new Rational(0,0);
    }
    public Rational minus(Rational b){
        return new Rational(0,0);
    }
    public Rational times(Rational b){
        return new Rational(0,0);
    }
    public Rational divides(Rational b){
        return new Rational(0,0);
    }
    public boolean equals(Rational that){
        if(that==this) return true;
        if(that==null) return false;
        if(that.getClass()!=this.getClass()) return false;
        return numerator==that.numerator&&denomitor==that.denomitor;
    }
    public String toString(){
        return numerator+"."+denomitor;
    }
}
