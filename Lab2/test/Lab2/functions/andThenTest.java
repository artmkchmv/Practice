package Lab2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class andThenTest {
    @Test
    public void testandThen() throws Exception {
        SqrFunction f = new SqrFunction();
        cosFunction g = new cosFunction();
        tgFunction h = new tgFunction();
        double result = f.andThen(g).andThen(h).apply(1);
        assertEquals(0.000179244, result, 1e-9);
    }
    @Test
    public void testandThen_2() throws Exception {
        SqrFunction h = new SqrFunction();
        cosFunction g = new cosFunction();
        tgFunction f = new tgFunction();
        double result = f.andThen(g).andThen(h).apply(2);
        assertEquals(-0.7659697023, result, 1e-9);
    }
}