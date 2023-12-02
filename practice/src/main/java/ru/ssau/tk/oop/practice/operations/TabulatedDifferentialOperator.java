package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.concurrent.SynchronizedTabulatedFunction;
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

    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] array = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount()];
        double[] yValues = new double[function.getCount()];
        for (int i = 0; i < function.getCount() - 1; i++) {
            xValues[i] = array[i].x;
            yValues[i] = (array[i + 1].y - array[i].y) / (array[i + 1].x - array[i].x);
        }
        xValues[function.getCount() - 1] = array[function.getCount() - 1].x;
        yValues[function.getCount() - 1] = yValues[function.getCount() - 2];
        return factory.create(xValues, yValues);
    }
    public SynchronizedTabulatedFunction deriveSynchronously(TabulatedFunction function) {
        SynchronizedTabulatedFunction synchronizedFunction = (function instanceof SynchronizedTabulatedFunction) ?
                (SynchronizedTabulatedFunction) function :
                new SynchronizedTabulatedFunction(function);

        return synchronizedFunction.doSynchronously(func -> new SynchronizedTabulatedFunction(derive(func)));
    }
}