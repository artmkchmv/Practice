package Lab2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    public void testSqrFunction() throws Exception {
        MathFunction func = new SqrFunction();
        double result = func.apply(1.6);
        assertEquals(2.56, result, 1e-9);
    }
}