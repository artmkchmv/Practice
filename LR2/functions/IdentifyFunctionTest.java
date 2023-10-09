package LR2.functions;

import LR2.functions.IdentifyFunction;
import LR2.functions.MathFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifyFunctionTest {

    @Test
    public void testIdentifyFunction() throws Exception {
        MathFunction func = new IdentifyFunction();
        double result = func.apply(15.256789);
        assertEquals(15.256789, result, 1e-9);
    }
}
