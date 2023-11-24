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
                System.out.println("After read: i = " + i + ", x = " + function.getX(i) + ", y = " + function.getY(i));
        }
    }
}