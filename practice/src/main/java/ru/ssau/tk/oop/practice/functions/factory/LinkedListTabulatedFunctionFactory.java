package ru.ssau.tk.oop.practice.functions.factory;

import ru.ssau.tk.oop.practice.functions.*;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}