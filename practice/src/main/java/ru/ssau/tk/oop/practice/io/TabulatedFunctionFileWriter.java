package ru.ssau.tk.oop.practice.io;

import java.io.*;

import ru.ssau.tk.oop.practice.functions.*;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try {
            try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
                 BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

                double[] xValue = {1.0, 2.0, 3.0, 4.0};
                double[] yValue = {0.0, 1.0, 2.0, 3.0};

                TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValue, yValue);
                TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValue, yValue);

                FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
                FunctionsIO.writeTabulatedFunction(linkedListWriter, linkedListFunction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
