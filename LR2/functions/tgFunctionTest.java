package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class tgFunctionTest {

    @Test
    public void testtgFunction() throws Exception {
        double result = tgFunction.apply(Math.PI / 4);
        assertEquals(1, result, 1e-9);
    }
}