package ru.ssau.tk.oop.practice.io;

import java.io.*;

import ru.ssau.tk.oop.practice.functions.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedOutputStream ArrayFileOutputStream = new BufferedOutputStream(new FileOutputStream("output/arrayfunction.bin")); BufferedOutputStream LinkedListFileOutputStream = new BufferedOutputStream(new FileOutputStream("output/linkedlistfunction.bin"))) {
            double[] xValues = {1.5, 2.6, 3.7};
            double[] yValues = {7.1, 8.2, 9.3};
            TabulatedFunction ArrayFunction = new ArrayTabulatedFunction(xValues, yValues);
            TabulatedFunction LinkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
            FunctionsIO.writeTabulatedFunction(ArrayFileOutputStream, ArrayFunction);
            FunctionsIO.writeTabulatedFunction(LinkedListFileOutputStream, LinkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}