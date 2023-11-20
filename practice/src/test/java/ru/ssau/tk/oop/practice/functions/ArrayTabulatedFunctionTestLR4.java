package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class ArrayTabulatedFunctionTestLR4 {

    double[] array1 = {1.5, 2.6, 3.7, 4.8, 5.9};
    double[] array2 = {6.1, 7.2, 8.3, 9.4, 10.5};
    ArrayTabulatedFunction array = new ArrayTabulatedFunction(array1, array2);

    @Test
    public void testArrayTabulatedFunctionConstructor1Thrown() throws Exception {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double[] array1 = {1.5};
                    double[] array2 = {6.1};
                    ArrayTabulatedFunction test_array1 = new ArrayTabulatedFunction(array1, array2);
                }
        );
    }

    @Test
    public void testArrayTabulatedFunctionConstructor2Thrown() throws Exception {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    MathFunction sqrFunc = new SqrFunction();
                    double x_From = 1.2;
                    double x_To = 3.3;
                    int len = 1;
                    ArrayTabulatedFunction test_array2 = new ArrayTabulatedFunction(sqrFunc, x_From, x_To, len);
                }
        );
    }

    @Test
    public void testgetXThrown1() throws Exception {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                    double result = array.getX(-1);
                }
        );
    }

    @Test
    public void testgetXThrown2() throws Exception {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                    double result = array.getX(10);
                }
        );
    }
    @Test
    public void testgetYThrown1() throws Exception {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                    double result = array.getY(-1);
                }
        );
    }

    @Test
    public void testgetYThrown2() throws Exception {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                    double result = array.getY(10);
                }
        );
    }

    @Test
    public void testfloorIndexOfXThrown() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    int result = array.floorIndexOfX(1.4);
                }
        );
    }

    @Test
    public void testinterpolate() throws Exception {
        double result = array.interpolate(5.3, 4.8, 5.9, 9.4, 10.5);
        assertEquals(9.9, result, 1e-9);
    }
    @Test
    public void testiterator1() throws Exception {
        Iterator<Point> iterator = array.iterator();
        int i = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            sum_x += array.getX(i);
            sum_point_x += point.x;
            sum_y += array.getY(i);
            sum_point_y += point.y;
            i++;
        }
        assertEquals(sum_x, sum_point_x, 1e-9);
        assertEquals(sum_y, sum_point_y, 1e-9);
    }

    @Test
    public void testiterator2() throws Exception {
        Iterator<Point> iterator = array.iterator();
        int i = 0;
        double sum_x = 0;
        double sum_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        for (Point point : array) {
            sum_x += array.getX(i);
            sum_point_x += point.x;
            sum_y += array.getY(i);
            sum_point_y += point.y;
            i++;
        }
        assertEquals(sum_x, sum_point_x, 1e-9);
        assertEquals(sum_y, sum_point_y, 1e-9);
    }
}
