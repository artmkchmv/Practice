package ru.ssau.tk.oop.practice.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CosFunctionTest {

    @Test
    public void testCosFunction() throws Exception {
        MathFunction func = new CosFunction();
        double result = func.apply(Math.PI / 3);
        assertEquals(0.5, result, 1e-9);
    }
}
