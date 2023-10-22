package LR3.functions;

public class IdentifyFunction implements MathFunction {
    public double apply(double x) {
        return x;
    }
    @Override public String toString(){
        return "f(x) = x";
    }
    @Override public boolean equals (Object o){
        return (o.getClass() == this.getClass());
    }
    @Override public int hashCode(){
        return this.getClass().hashCode();
    }
    @Override public Object clone(){
        return new IdentifyFunction();
    }
}

