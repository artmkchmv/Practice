package ru.ssau.tk.oop.practice.functions;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTestLR3 {

    @org.junit.jupiter.api.Test
    void testToString() throws Exception {
        double[] xValues = {2.0, 3.0, 4.0};
        double[] yValues = {5.0, 6.0, 7.0};
        ArrayTabulatedFunction fun1 = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(fun1.toString(), "[2.0, 3.0, 4.0]" + "\n" + "[5.0, 6.0, 7.0]");
    }

    @org.junit.jupiter.api.Test
    void testEquals() throws Exception {
        double[] xValues1 = {2.0, 3.0, 4.0};
        double[] yValues1 = {5.0, 6.0, 7.0};
        double[] xValues2 = {2.0, 3.0, 4.0};
        double[] yValues2 = {5.0, 6.0, 7.0};
        ArrayTabulatedFunction fun1 = new ArrayTabulatedFunction(xValues1, yValues1);
        ArrayTabulatedFunction fun2 = new ArrayTabulatedFunction(xValues2, yValues2);
        assertTrue(fun1.equals(fun2));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() throws Exception {
        double[] xValues1 = {2.0, 3.0, 4.0};
        double[] yValues1 = {5.0, 6.0, 7.0};
        double[] xValues2 = {2.0, 3.0, 4.0};
        double[] yValues2 = {5.0, 6.0, 7.0};
        ArrayTabulatedFunction fun1 = new ArrayTabulatedFunction(xValues1, yValues1);
        ArrayTabulatedFunction fun2 = new ArrayTabulatedFunction(xValues2, yValues2);
        assertEquals(fun1.hashCode(), fun2.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testClone() throws Exception {
        double[] xValues1 = {2.0, 3.0, 4.0};
        double[] yValues1 = {5.0, 6.0, 7.0};
        ArrayTabulatedFunction fun1 = new ArrayTabulatedFunction(xValues1, yValues1);
        assertEquals(fun1, fun1.clone());
    }
}