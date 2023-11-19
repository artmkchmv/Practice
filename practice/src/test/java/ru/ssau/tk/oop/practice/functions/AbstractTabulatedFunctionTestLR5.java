package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractTabulatedFunctionTestLR5 {

    @Test
    public void testtoStringList() throws Exception {
        double[] xValues = {1.5, 2.6, 3.7};
        double[] yValues = {7.1, 8.2, 9.3};
        LinkedListTabulatedFunction lst = new LinkedListTabulatedFunction(xValues, yValues);
        String result = "LinkedListTabulatedFunction size = 3\n[1.5; 7.1]\n[2.6; 8.2]\n[3.7; 9.3]\n";
        assertEquals(result, lst.toString());
    }

    @Test
    public void testtoStringArray() throws Exception {
        double[] xValues = {1.5, 2.6, 3.7};
        double[] yValues = {7.1, 8.2, 9.3};
       ArrayTabulatedFunction lst = new ArrayTabulatedFunction(xValues, yValues);
        String result = "ArrayTabulatedFunction size = 3\n[1.5; 7.1]\n[2.6; 8.2]\n[3.7; 9.3]\n";
        assertEquals(result, lst.toString());
    }
}
