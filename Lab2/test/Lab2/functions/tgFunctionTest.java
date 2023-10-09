package Lab2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class tgFunctionTest {

    @Test
    public void testtgFunction() throws Exception {
        MathFunction func = new tgFunction();
        double result = func.apply(Math.PI / 4);
        assertEquals(1, result, 1e-9);
    }
}