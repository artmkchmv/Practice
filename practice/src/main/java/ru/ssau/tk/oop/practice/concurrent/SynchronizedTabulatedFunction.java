package ru.ssau.tk.oop.practice.concurrent;

import java.util.*;

import java.util.concurrent.locks.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.operations.*;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;
    private final Lock lock = new ReentrantLock();

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public int getCount() {
        lock.lock();
        try {
            return function.getCount();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getX(int index) {
        lock.lock();
        try {
            return function.getX(index);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getY(int index) {
        lock.lock();
        try {
            return function.getY(index);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setY(int index, double y) {
        lock.lock();
        try {
            function.setY(index, y);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int indexOfX(double x) {
        lock.lock();
        try {
            return function.indexOfX(x);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int indexOfY(double y) {
        lock.lock();
        try {
            return function.indexOfY(y);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double leftBound() {
        lock.lock();
        try {
            return function.leftBound();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double rightBound() {
        lock.lock();
        try {
            return function.rightBound();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        lock.lock();
        try {
            Point[] copyArray = TabulatedFunctionOperationService.asPoints(function);
            return new Iterator<Point>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return (i < copyArray.length);
                }

                @Override
                public Point next() {
                    if (hasNext()) {
                        Point pointer;
                        pointer = copyArray[i++];
                        return pointer;
                    } else throw new NoSuchElementException();
                }
            };
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double apply(double x) {
        lock.lock();
        try {
            return function.apply(x);
        } finally {
            lock.unlock();
        }
    }
}