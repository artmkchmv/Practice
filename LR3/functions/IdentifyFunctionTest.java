package LR3.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifyFunctionTest {

    @Test
    void ToStringTest() throws Exception {
        MathFunction fun = new IdentifyFunction();
        assertTrue(fun.toString().equals("f(x) = x"));
    }

    @Test
    void EqualsTest() throws Exception{
        IdentifyFunction fun1 = new IdentifyFunction();
        IdentifyFunction fun2 = new IdentifyFunction();
        assertTrue(fun1.equals(fun2));
    }

    @Test
    void HashCodeTest() throws Exception{
        IdentifyFunction fun1 = new IdentifyFunction();
        assertEquals(fun1.hashCode(), fun1.hashCode());
    }

    @Test
    void CloneTest() throws Exception{
        IdentifyFunction fun1 = new IdentifyFunction();
        Object fun2 = fun1.clone();
        assertTrue(fun1.equals(fun2));
    }
}