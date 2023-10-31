package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifyFunctionTestLR2 {

    @Test
    public void testIdentifyFunction() throws Exception {
        MathFunction func = new IdentifyFunction();
        double result = func.apply(15.256789);
        assertEquals(15.256789, result, 1e-9);
    }
}