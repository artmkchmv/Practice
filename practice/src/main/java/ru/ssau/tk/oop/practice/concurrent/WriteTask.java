package ru.ssau.tk.oop.practice.concurrent;

import ru.ssau.tk.oop.practice.functions.*;

public class WriteTask implements Runnable {
    private TabulatedFunction function;
    private double value;

    public WriteTask(TabulatedFunction function, double value) {
        this.function = function;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            function.setY(i, value);
            System.out.printf("Writing for index %d complete", i);
        }
    }
}