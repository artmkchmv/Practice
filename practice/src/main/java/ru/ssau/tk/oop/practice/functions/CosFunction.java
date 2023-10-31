package ru.ssau.tk.oop.practice.functions;

import static java.lang.Math.cos;

public class CosFunction implements MathFunction {
    public double apply(double x) {
        return cos(x);
    }
}