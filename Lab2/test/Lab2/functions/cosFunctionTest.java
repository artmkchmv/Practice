package Lab2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class cosFunctionTest {

    @Test
    public void testcosFunction() throws Exception {
        MathFunction func = new cosFunction();
        double result = func.apply(Math.PI / 3);
        assertEquals(0.5, result, 1e-9);
    }
}