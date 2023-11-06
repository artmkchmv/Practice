package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> ((function.apply(x + step) - function.apply(x)) / step);
    }
}