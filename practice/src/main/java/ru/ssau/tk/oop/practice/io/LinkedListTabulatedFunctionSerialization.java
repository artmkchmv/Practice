package ru.ssau.tk.oop.practice.io;

import java.io.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.operations.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            double[] xValues = {1.5, 2.6, 3.7};
            double[] yValues = {7.1, 8.2, 9.3};
            TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction FirstDerivative = operator.derive(function);
            TabulatedFunction SecondDerivative = operator.derive(FirstDerivative);
            FunctionsIO.serialize(outputStream, function);
            FunctionsIO.serialize(outputStream, FirstDerivative);
            FunctionsIO.serialize(outputStream, SecondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(inputStream));
            System.out.println(FunctionsIO.deserialize(inputStream));
            System.out.println(FunctionsIO.deserialize(inputStream));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}