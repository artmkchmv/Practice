package ru.ssau.tk.oop.practice.functions;

import ru.ssau.tk.oop.practice.exceptions.*;

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
        int i = 1;
        while (i < xValues.length) {
            if (xValues[i] - xValues[i - 1] < 1e-9) throw new ArrayIsNotSortedException("array is not Sorted");
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append(getClass().getSimpleName());
        tempString.append(" size = ");
        tempString.append(getCount());
        tempString.append("\n");
        for (Point point : this) {
            tempString.append("[");
            tempString.append(point.x);
            tempString.append("; ");
            tempString.append(point.y);
            tempString.append("]");
            tempString.append("\n");
        }
        return tempString.toString();
    }
}