package LR2.functions;

import static java.lang.Math.pow;
import static java.lang.Math.cos; //X
import static java.lang.Math.tan; //Y

//X или Y
interface MathFunction {
    static double apply(double x) {
        return x;
    }
}

//X
class IdentifyFunction implements MathFunction {
    public static double apply(double x) {
        return x;
    }
}

//Y
class SqrFunction implements MathFunction {
    public static double apply(double x) {
        return pow(x, 2);
    }
}

//X
class cosFunction implements MathFunction {
    public static double apply(double x) {
        return cos(x);
    }
}

//Y
class tgFunction implements MathFunction {
    public static double apply(double x) {
        return tan(x);
    }
}