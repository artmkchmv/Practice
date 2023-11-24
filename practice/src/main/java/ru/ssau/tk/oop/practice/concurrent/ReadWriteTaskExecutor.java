package ru.ssau.tk.oop.practice.concurrent;

import ru.ssau.tk.oop.practice.functions.*;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread readThread = new Thread(new ReadTask(function));
        Thread writeThread = new Thread(new WriteTask(function, 0.5));
        readThread.start();
        writeThread.start();
    }
}
