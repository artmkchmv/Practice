package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.*;

import static java.lang.Double.*;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    SteppingDifferentialOperator(double step) {
        if ((step <= 0) || (step == POSITIVE_INFINITY) || (step == NaN))
            throw new IllegalArgumentException();
        else this.step = step;

    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }
}