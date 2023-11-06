package ru.ssau.tk.oop.practice.operations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

public class TabulatedDifferentialOperatorTest {
    double[] xValues = {1, 2, 3, 4, 5};
    double[] yValues = {1, 4, 9, 16, 25};

    @Test
    public void testsetFactory1() throws Exception {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(factory);
        assertEquals(factory.getClass(), operator.getFactory().getClass());
    }

    @Test
    public void testsetFactory2() throws Exception {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(factory);
        assertEquals(factory.getClass(), operator.getFactory().getClass());
    }

    @Test
    public void testgetFactory1() throws Exception {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(factory);
        assertEquals(operator.getFactory(), factory);
    }

    @Test
    public void testgetFactory2() throws Exception {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(factory);
        assertEquals(operator.getFactory(), factory);
    }

    @Test
    public void testderive1() throws Exception {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction derivative_function = operator.derive(function);
        assertEquals(3, derivative_function.getY(0));
        assertEquals(5, derivative_function.getY(1));
        assertEquals(7, derivative_function.getY(2));
        assertEquals(9, derivative_function.getY(3));
        assertEquals(9, derivative_function.getY(4));
    }

/*    @Test
    public void testderive2() throws Exception {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction derivative_function = operator.derive(function);
        assertEquals(3, derivative_function.getY(0));
        assertEquals(5, derivative_function.getY(1));
        assertEquals(7, derivative_function.getY(2));
        assertEquals(9, derivative_function.getY(3));
        assertEquals(9, derivative_function.getY(3));
    }*/
}