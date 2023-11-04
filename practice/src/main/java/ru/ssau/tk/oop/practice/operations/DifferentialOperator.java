package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.*;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}