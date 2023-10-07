package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    public void testSqrFunction() throws Exception {
        double result = SqrFunction.apply(1.6);
        assertEquals(2.56, result, 1e-9);
    }
}