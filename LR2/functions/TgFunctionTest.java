package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TgFunctionTest {

    @Test
    public void testTgFunction() throws Exception {
        MathFunction func = new TgFunction();
        double result = func.apply(Math.PI / 4);
        assertEquals(1, result, 1e-9);
    }
}