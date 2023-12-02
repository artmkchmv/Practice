package ru.ssau.tk.oop.practice.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import ru.ssau.tk.oop.practice.functions.*;

public class SynchronizedTabulatedFunctionTest {
    double[] array1 = {1.5, 2.6, 3.7, 4.8, 5.9};
    double[] array2 = {6.1, 7.2, 8.3, 9.4, 10.5};

    SynchronizedTabulatedFunction function = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(array1, array2));

    @Test
    public void testgetCount() throws Exception {
        int result = function.getCount();
        assertEquals(5, result, 1e-9);
    }

    @Test
    public void testgetX() throws Exception {
        double result = function.getX(1);
        assertEquals(2.6, result, 1e-9);
    }

    @Test
    public void testgetY() throws Exception {
        double result = function.getY(1);
        assertEquals(7.2, result, 1e-9);
    }

    @Test
    public void testindexOfX() throws Exception {
        int result = function.indexOfX(2.6);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testindexOfY() throws Exception {
        int result = function.indexOfY(22);
        assertEquals(-1, result, 1e-9);
    }

    @Test
    public void testleftBound() throws Exception {
        double result = function.leftBound();
        assertEquals(1.5, result, 1e-9);
    }

    @Test
    public void testrightBound() throws Exception {
        double result = function.rightBound();
        assertEquals(5.9, result, 1e-9);
    }

    @Test
    public void testiterator1() throws Exception {
        Iterator<Point> iterator = function.iterator();
        int i = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            sum_x += function.getX(i);
            sum_point_x += point.x;
            sum_y += function.getY(i);
            sum_point_y += point.y;
            i++;
        }
        assertEquals(sum_x, sum_point_x, 1e-9);
        assertEquals(sum_y, sum_point_y, 1e-9);
    }

    @Test
    public void testiterator2() throws Exception {
        Iterator<Point> iterator = function.iterator();
        int i = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        for (Point point : function) {
            sum_x += function.getX(i);
            sum_point_x += point.x;
            sum_y += function.getY(i);
            sum_point_y += point.y;
            i++;
        }
        assertEquals(sum_x, sum_point_x, 1e-9);
        assertEquals(sum_y, sum_point_y, 1e-9);
    }

    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = func -> {
            double sum = 0;
            for (Point el : function)
                sum += el.y;
            return sum;
        };
        double sumOfY = function.doSynchronously(operation);
        assertEquals(41.5, sumOfY,1e-9);
    }
}
