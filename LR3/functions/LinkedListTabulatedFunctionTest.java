package LR3.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    public void testtoString() throws Exception {
        LinkedListTabulatedFunction.Node testNode = new LinkedListTabulatedFunction.Node(0.1111, 9.09);
        assertEquals("(0.1111; 9.09)", testNode.toString());
    }

    @Test
    public void testequals() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(1.02345, 9.0876);
        LinkedListTabulatedFunction.Node testNode_2 = new LinkedListTabulatedFunction.Node(1.02345, 9.0875);
        assertEquals(false, testNode_1.equals(testNode_2));
    }

    @Test
    public void testhashCode() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        LinkedListTabulatedFunction.Node testNode_2 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        assertEquals(testNode_1.hashCode(), testNode_2.hashCode());
    }

    @Test
    public void testclone() throws Exception {
        LinkedListTabulatedFunction.Node testNode_1 = new LinkedListTabulatedFunction.Node(123.456, 78.9);
        assertEquals(testNode_1, testNode_1.clone());
    }
}