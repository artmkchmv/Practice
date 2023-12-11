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

        double[] xValues = new double[b.getCount()];
        double[] yValues = new double[b.getCount()];

        for (int i = 0; i < b.getCount(); i++) {
            if (arraysA[i].x != arraysB[i].x) throw new InconsistentFunctionsException();

            xValues[i] = arraysA[i].x;
            yValues[i] = operation.apply(arraysA[i].y, arraysB[i].y);
        }
        return factory.create(xValues, yValues);
    }

    protected TabulatedFunction doOperation2(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount())
            throw new InconsistentFunctionsException();

        double[] xValuesA = new double[a.getCount()];
        double[] yValuesA = new double[a.getCount()];
        double[] xValuesB = new double[b.getCount()];
        double[] yValuesB = new double[b.getCount()];
        double[] xValuesResult = new double[a.getCount()];
        double[] yValuesResult = new double[b.getCount()];

        for (int i = 0; i < a.getCount(); i++) {
            xValuesA[i] = a.getX(i);
            yValuesA[i] = a.getY(i);
            xValuesB[i] = b.getX(i);
            yValuesB[i] = b.getY(i);
        }

        for (int i = 0; i < a.getCount(); i++) {
            if (xValuesA[i] != xValuesB[i])
                throw new InconsistentFunctionsException();
            xValuesResult[i] = xValuesA[i];
            yValuesResult[i] = operation.apply(yValuesA[i], yValuesB[i]);
        }
        return factory.create(xValuesResult, yValuesResult);
    }

    public TabulatedFunction Addition(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u + v;
        return doOperation2(a, b, op);
    }

    public TabulatedFunction Subtraction(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u - v;
        return doOperation2(a, b, op);
    }

    public TabulatedFunction Multiplication(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u * v;
        return doOperation2(a, b, op);
    }

    public TabulatedFunction Division(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> {
            if (v == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return u / v;
        };
        return doOperation2(a, b, op);
    }
}