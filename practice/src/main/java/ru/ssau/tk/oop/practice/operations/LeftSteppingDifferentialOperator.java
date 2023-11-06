package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {
    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> (function.apply(x) - function.apply(x - step)) / step;
    }
}