package ru.ssau.tk.oop.practice.functions;

public class ConstantFunction implements MathFunction {
    private final double val_x;

    public ConstantFunction(double val_x) {
        this.val_x = val_x;
    }

    public double getVal_x() {
        return val_x;
    }

    @Override
    public double apply(double x) {
        return val_x;
    }
}