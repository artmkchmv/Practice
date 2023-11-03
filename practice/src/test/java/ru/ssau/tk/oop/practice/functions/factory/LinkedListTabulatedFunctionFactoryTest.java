package ru.ssau.tk.oop.practice.functions.factory;

import ru.ssau.tk.oop.practice.functions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTabulatedFunctionFactoryTest {

    @Test
    void testLinkedListTabulatedFunctionFactory() {
        double[] xValues = {1.3, 2.4, 3.5, 4.6};
        double[] yValues = {5.7, 6.8, 7.9, 8.1};
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction);
    }
}