package LR2.functions;

import LR2.functions.ZeroFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    @Test
    public void testConstantFunction_1() throws Exception {
        ZeroFunction func = new ZeroFunction();
        double result = func.apply(0.4);
        assertEquals(0.0, result, 1e-9);
    }
}