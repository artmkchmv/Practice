package LR2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class andThenTest {
    @Test
    public void testandThen() throws Exception {
        SqrFunction f = new SqrFunction();
        CosFunction g = new CosFunction();
        TgFunction h = new TgFunction();
        double result = f.andThen(g).andThen(h).apply(1);
        assertEquals(0.000179244, result, 1e-9);
    }
    @Test
    public void testandThen_2() throws Exception {
        SqrFunction h = new SqrFunction();
        CosFunction g = new CosFunction();
        TgFunction f = new TgFunction();
        double result = f.andThen(g).andThen(h).apply(2);
        assertEquals(-0.7659697023, result, 1e-9);
    }
}