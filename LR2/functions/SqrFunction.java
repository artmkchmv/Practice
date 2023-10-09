package LR2.functions;

import static java.lang.Math.pow;

class SqrFunction implements MathFunction {
    public double apply(double x) {
        return pow(x, 2);
    }
}
