package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {
    @Test
    public void testConstantFunction_1() throws Exception {
        UnitFunction func = new UnitFunction();
        double result = func.apply(0.4);
        assertEquals(1, result, 1e-9);
    }
}