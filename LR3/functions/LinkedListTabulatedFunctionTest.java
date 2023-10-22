package LR3.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    public void testtoStringNode() throws Exception {
        LinkedListTabulatedFunction.Node testNode = new LinkedListTabulatedFunction.Node(0.1111, 9.09);
        assertEquals("(0.1111; 9.09)", testNode.toString());
    }

    @Test
    public void testequalsNode() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(1.02345, 9.0876);
        LinkedListTabulatedFunction.Node testNode_2 = new LinkedListTabulatedFunction.Node(1.02345, 9.0875);
        assertEquals(false, testNode_1.equals(testNode_2));
    }

    @Test
    public void testhashCodeNode() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        LinkedListTabulatedFunction.Node testNode_2 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        assertEquals(testNode_1.hashCode(), testNode_2.hashCode());
    }

    @Test
    public void testcloneNode() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        assertEquals(testNode_1, testNode_1.clone());
    }
    @Test
    public void testtoStringList() throws Exception {
        double[] xValues = {1.5, 2.6, 3.7};
        double[] yValues = {7.1, 8.2, 9.3};
        LinkedListTabulatedFunction lst = new LinkedListTabulatedFunction(xValues, yValues);
        String result = "(1.5; 7.1), (2.6; 8.2), (3.7; 9.3)";
        assertEquals(result, lst.toString());
    }

    @Test
    public void testequalsList() throws Exception {
        double[] xValues_1 = {1.5, 2.6, 3.7};
        double[] yValues_1 = {7.1, 8.2, 9.3};
        double[] xValues_2 = {1.1, 2.9, 3.2};
        double[] yValues_2 = {7.4, 8.6, 9.5};
        LinkedListTabulatedFunction lst_1 = new LinkedListTabulatedFunction(xValues_1, yValues_1);
        LinkedListTabulatedFunction lst_2 = new LinkedListTabulatedFunction(xValues_2, yValues_2);
        assertEquals(false, lst_1.equals(lst_2));
    }

    @Test
    public void testhashCodeList() throws Exception {
        double[] xValues_1 = {1.5, 2.6, 3.7};
        double[] yValues_1 = {7.1, 8.2, 9.3};
        double[] xValues_2 = {1.5, 2.6, 3.7};
        double[] yValues_2 = {7.1, 8.2, 9.3};
        LinkedListTabulatedFunction lst_1 = new LinkedListTabulatedFunction(xValues_1, yValues_1);
        LinkedListTabulatedFunction lst_2 = new LinkedListTabulatedFunction(xValues_2, yValues_2);
        assertEquals(lst_1.hashCode(), lst_2.hashCode());
    }

    @Test
    public void testcloneList() throws Exception {
        double[] xValues = {1.5, 2.6, 3.7};
        double[] yValues = {7.1, 8.2, 9.3};
        LinkedListTabulatedFunction lst = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(lst, lst.clone());
    }
}