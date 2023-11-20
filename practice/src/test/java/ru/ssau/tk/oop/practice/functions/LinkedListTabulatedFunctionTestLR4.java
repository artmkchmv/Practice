package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class LinkedListTabulatedFunctionTestLR4 {

    double[] array1 = {1.5, 2.6, 3.7, 4.8, 5.9};
    double[] array2 = {6.1, 7.2, 8.3, 9.4, 10.5};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(array1, array2);

    @Test
    public void testLinkedListTabulatedFunctionConstructor1Thrown() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double[] array1 = {1.5};
                    double[] array2 = {6.1};
                    LinkedListTabulatedFunction test_list1 = new LinkedListTabulatedFunction(array1, array2);
                }
        );
    }

    @Test
    public void testLinkedListTabulatedFunctionConstructor2Thrown() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    MathFunction sqrFunc = new SqrFunction();
                    double x_From = 1.2;
                    double x_To = 3.3;
                    int len = 1;
                    LinkedListTabulatedFunction test_list2 = new LinkedListTabulatedFunction(sqrFunc, x_From, x_To, len);
                }
        );
    }

    @Test
    public void testgetXThrown1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double result = list.getX(-1);
                }
        );
    }

    @Test
    public void testgetXThrown2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double result = list.getX(10);
                }
        );
    }

    @Test
    public void testgetYThrown1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double result = list.getY(-1);
                }
        );
    }

    @Test
    public void testgetYThrown2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    double result = list.getY(10);
                }
        );
    }

    @Test
    public void testfloorIndexOfXThrown() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    int result = list.floorIndexOfX(1.4);
                }
        );
    }

    @Test
    public void testinterpolate1() throws Exception {
        double result = list.interpolate(3.7, 2);
        assertEquals(8.3, result, 1e-9);
    }

    @Test
    public void testinterpolate2() throws Exception {
        double result = list.interpolate(5.3, 4.8, 5.9, 9.4, 10.5);
        assertEquals(9.9, result, 1e-9);
    }

    @Test
    public void testiterator1() throws Exception {
        Iterator<Point> iterator = list.iterator();
        LinkedListTabulatedFunction.Node node = list.getNode(0);
        double sum_node_x = 0;
        double sum_node_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            sum_node_x += node.x;
            sum_point_x += point.x;
            sum_node_y += point.y;
            sum_point_y += point.y;
            node = node.next;
        }
        assertEquals(sum_node_x, sum_point_x, 1e-9);
        assertEquals(sum_node_y, sum_point_y, 1e-9);
    }

    @Test
    public void testiterator2() throws Exception {
        Iterator<Point> iterator = list.iterator();
        LinkedListTabulatedFunction.Node node = list.getNode(0);
        double sum_node_x = 0;
        double sum_node_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        for (Point point : list) {
            sum_node_x += node.x;
            sum_point_x += point.x;
            sum_node_y += point.y;
            sum_point_y += point.y;
            node = node.next;
        }
        assertEquals(sum_node_x, sum_point_x, 1e-9);
        assertEquals(sum_node_y, sum_point_y, 1e-9);
    }
}