package ru.ssau.tk.oop.practice.functions;

import ru.ssau.tk.oop.practice.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.oop.practice.exceptions.ArrayIsNotSortedException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    abstract protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    abstract public double apply(double x);

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("different lenghts of arrays");
        }
    }

    public static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] > xValues[i + 1])
                throw new ArrayIsNotSortedException("Array not sorted");
        }
    }
}