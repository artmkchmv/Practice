package ru.ssau.tk.oop.practice.concurrent;

import ru.ssau.tk.oop.practice.functions.*;

public class ReadTask implements Runnable {
    private TabulatedFunction function;

    public ReadTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
                System.out.printf("After read: i = %d, x = %f, y = %f", i, function.getX(i), function.getY(i));
        }
    }
}