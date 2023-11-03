package ru.ssau.tk.oop.practice.functions.factory;

import ru.ssau.tk.oop.practice.functions.*;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
}