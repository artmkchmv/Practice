package ru.ssau.tk.oop.practice.io;

import java.io.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.operations.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
                double[] xValues = {2, 3, 4};
                double[] yValues = {2.5, 3.5, 4.5};

                TabulatedDifferentialOperator differentOperator = new TabulatedDifferentialOperator();

                TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
                TabulatedFunction firstDifferentArrayFunction = differentOperator.derive(arrayFunction);
                TabulatedFunction secondDifferentArrayFunction = differentOperator.derive(firstDifferentArrayFunction);

                FunctionsIO.serialize(bufferedOutputStream, arrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, firstDifferentArrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, secondDifferentArrayFunction);
            }
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
                System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
