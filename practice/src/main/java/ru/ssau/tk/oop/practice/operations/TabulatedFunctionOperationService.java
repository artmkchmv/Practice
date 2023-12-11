package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.oop.practice.functions.*;
import ru.ssau.tk.oop.practice.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] asPointsArr = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            asPointsArr[i] = point;
            i++;
        }
        return asPointsArr;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    protected TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) throw new InconsistentFunctionsException();
        Point[] arraysA = asPoints(a);
        Point[] arraysB = asPoints(b);

        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];

        for (int i = 0; i < a.getCount(); i++) {
            if (arraysA[i].x != arraysB[i].x) throw new InconsistentFunctionsException();

            xValues[i] = arraysA[i].x;
            yValues[i] = operation.apply(arraysA[i].y, arraysB[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction Addition(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u + v;
        return doOperation(a, b, op);
    }

    public TabulatedFunction Subtraction(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u - v;
        return doOperation(a, b, op);
    }

    public TabulatedFunction Multiplication(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u * v;
        return doOperation(a, b, op);
    }

    public TabulatedFunction Division(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> {
            if (v == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return u / v;
        };
        return doOperation(a, b, op);
    }
}