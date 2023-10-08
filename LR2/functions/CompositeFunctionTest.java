package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeFunctionTest {

    @Test
    public void testCompositeFunction() throws Exception {
        IdentifyFunction X = new IdentifyFunction();
        cosFunction COS = new cosFunction();
        CompositeFunction func = new CompositeFunction(COS, X);
        double result = func.apply(Math.PI);
        assertEquals(-1, result, 1e-9);
    }
}