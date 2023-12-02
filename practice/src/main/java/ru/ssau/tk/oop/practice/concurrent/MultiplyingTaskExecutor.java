package ru.ssau.tk.oop.practice.concurrent;

import ru.ssau.tk.oop.practice.functions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 20000);
        int numberOfThreads = 10;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            Runnable task = new MultiplyingTask(tabulatedFunction, latch);
            executor.execute(task);
        }

        Thread.sleep(1000);
        executor.shutdown();

        System.out.println(tabulatedFunction);
    }
}
