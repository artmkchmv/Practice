package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class cosFunctionTest {

    @Test
    public void testcosFunction() throws Exception {
        double result = cosFunction.apply(Math.PI / 3);
        assertEquals(0.5, result, 1e-9);
    }
}