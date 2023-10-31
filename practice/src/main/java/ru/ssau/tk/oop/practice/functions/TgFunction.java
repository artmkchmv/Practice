package ru.ssau.tk.oop.practice.functions;

import static java.lang.Math.tan;

public class TgFunction implements MathFunction {
    public double apply(double x) {
        return tan(x);
    }
}