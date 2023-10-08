package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTabulatedFunctionTest {
    double[] array1 = {1.5, 2.6, 3.7, 4.8, 5.9};
    double[] array2 = {6.1, 7.2, 8.3, 9.4, 10.5};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(array1, array2);

    @Test
    public void testgetCount() {
        int result = list.getCount();
        assertEquals(5, result, 1e-9);
    }

    @Test
    public void testleftBound() {
        double result = list.leftBound();
        assertEquals(1.5, result, 1e-9);
    }

    @Test
    public void testrightBound() {
        double result = list.rightBound();
        assertEquals(5.9, result, 1e-9);
    }

    @Test
    public void testgetX() {
        double result = list.getX(3);
        assertEquals(4.8, result, 1e-9);
    }

    @Test
    public void testgetY() {
        double result = list.getY(3);
        assertEquals(9.4, result, 1e-9);
    }

    @Test
    public void testindexOfX() {
        int result = list.indexOfX(2.6);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testindexOfY() {
        int result = list.indexOfY(7.2);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testfloorIndexOfX() {
        int result = list.floorIndexOfX(3);
        assertEquals(1, result, 1e-9);
    }
}