package LR2.functions;

import LR2.functions.UnitFunction;
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