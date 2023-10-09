package LR2.functions;

import LR2.functions.ConstantFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstantFunctionTest{

    @Test
    public void testConstantFunction_1() throws Exception {
        ConstantFunction func = new ConstantFunction(0.9);
        double result = func.apply(0.4);
        assertEquals(0.9, result, 1e-9);
    }
    @Test
    public void testConstantFunction_2() throws Exception {
        ConstantFunction func = new ConstantFunction(0.9);
        double result = func.getVal_x();
        assertEquals(0.9, result, 1e-9);
    }
}