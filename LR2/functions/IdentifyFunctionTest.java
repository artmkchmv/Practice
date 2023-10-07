package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifyFunctionTest {

    @Test
    public void testIdentifyFunction() throws Exception {
        double result = IdentifyFunction.apply(15.6);
        assertEquals(15.6, result, 1e-9);
    }
}