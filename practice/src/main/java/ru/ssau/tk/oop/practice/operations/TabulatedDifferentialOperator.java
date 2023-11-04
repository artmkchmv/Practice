package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.*;
import ru.ssau.tk.oop.practice.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    protected TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {

        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }
    //unfinished
    public TabulatedFunction derive(TabulatedFunction function) {
        return function;
    }
}