package ru.ssau.tk.oop.practice.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.ssau.tk.oop.practice.functions.*;

public class DifferentialOperatorTest {
    MathFunction cos = new CosFunction();

    @Test
    public void LeftSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator leftOperation = new LeftSteppingDifferentialOperator(5);
        MathFunction differentialCos = leftOperation.derive(cos);
        assertEquals(0.238789, differentialCos.apply(1), 0.6);
    }

    @Test
    public void RightSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator rightOperation = new RightSteppingDifferentialOperator(5);
        MathFunction differentialCos = rightOperation.derive(cos);
        assertEquals(0.0839736, differentialCos.apply(1), 0.7);

    }
}