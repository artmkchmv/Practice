package ru.ssau.tk.oop.practice.functions;

import java.io.Serializable;

import java.util.*;

import static java.lang.Math.*;

import ru.ssau.tk.oop.practice.exceptions.*;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = 1L;
    private final double[] xValues;
    private final double[] yValues;
    protected int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 || yValues.length < 2)
            throw new IllegalArgumentException("table length is less than the required minimum");
        else {
            checkLengthIsTheSame(xValues, yValues);
            checkSorted(xValues);
            checkSorted(yValues);

            this.xValues = Arrays.copyOf(xValues, xValues.length);
            this.yValues = Arrays.copyOf(yValues, yValues.length);
            count = xValues.length;
        }
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2)
            throw new IllegalArgumentException("table length is less than the required minimum");
        else {
            xValues = new double[count];
            yValues = new double[count];
            double epsilon;
            if (xTo - xFrom > pow(10, -9)) {
                epsilon = (xTo - xFrom) / (count - 1);
                for (int i = 0; i < count; i++) {
                    xValues[i] = xFrom + i * epsilon;
                    yValues[i] = source.apply(xFrom + i * epsilon);
                }
            } else if (xFrom - xTo > pow(10, -9)) {

                epsilon = (xFrom - xTo) / (count - 1);
                for (int i = 0; i < count; i++) {
                    xValues[i] = xFrom + i * epsilon;
                    yValues[i] = source.apply(xFrom + i * epsilon);
                }
            } else {
                for (int i = 0; i < count; i++) {
                    xValues[i] = xFrom;
                    yValues[i] = source.apply(xFrom);
                }
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        return xValues[index];
    }

    public double getY(int index) {
        return yValues[index];
    }

    public void setY(int index, double val) {
        yValues[index] = val;
    }

    public double leftBound() {
        return xValues[0];
    }

    public double rightBound() {
        return xValues[count - 1];
    }

    public int indexOfX(double x) {
        int i = 0;
        while (i < count) {
            if (abs(xValues[i] - x) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }

    public int indexOfY(double y) {
        int i = 0;
        while (i < count) {
            if (abs(yValues[i] - y) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < leftBound())
            throw new IllegalArgumentException("x is less than the left bound");
        int i = 0;
        if (xValues[i] - x > 1e-9)
            return 0;
        else if (x - xValues[count - 1] > 1e-9)
            return count - 1;
        else {
            while (true) {
                if (abs(xValues[i] - x) < 1e-9)
                    return i;
                else if (xValues[i] - x > 1e-9)
                    return i - 1;
                i++;
            }
        }
    }

    protected double extrapolateLeft(double x) {
        if (count == 1)
            return yValues[0];
        else
            return (yValues[0] + ((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0]));
    }

    protected double extrapolateRight(double x) {
        if (count == 1)
            return yValues[0];
        else
            return (yValues[count - 2] + ((yValues[count - 1] - yValues[count - 2]) / (xValues[count - 1] - xValues[count - 2])) * (x - xValues[count - 2]));
    }

    protected double interpolate(double x, int floorIndex) {

        if (getX(floorIndex-1)<x && x< getX(floorIndex)) {
            double rightX = getX(floorIndex);
            double leftX = getX(floorIndex - 1);
            double rightY = getY(floorIndex);
            double leftY = getY(floorIndex - 1);
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
        }
        else
            throw new InterpolationException("X is not in the interval");
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }

    @Override
    public double apply(double x) {
        if (xValues[0] - x > 1e-9) {
            return extrapolateLeft(x);
        } else if (x - xValues[count - 1] > 1e-9) {
            return extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass())
            return false;
        else {
            return (Arrays.equals(((ArrayTabulatedFunction) o).xValues, xValues) &&
                    Arrays.equals(((ArrayTabulatedFunction) o).yValues, yValues));
        }
    }

    @Override
    public int hashCode() {
        return 31 * count + 37 * Arrays.hashCode(xValues) + 131 * Arrays.hashCode(yValues);
    }

    @Override
    public Object clone() {
        ArrayTabulatedFunction copy = new ArrayTabulatedFunction(xValues.clone(), yValues.clone());
        return copy;
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return (i < count);
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(xValues[i], yValues[i]);
                    ++i;
                    return point;
                } else throw new UnsupportedOperationException();
            }
        };
    }
}