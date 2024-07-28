package ru.ssau.tk.oop.practice.functions;

import static java.lang.Math.sin;

public class SinFunction implements MathFunction {
    public double apply(double x) { return sin(x); }
}
