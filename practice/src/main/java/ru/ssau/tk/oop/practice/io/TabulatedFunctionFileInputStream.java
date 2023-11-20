package ru.ssau.tk.oop.practice.io;

import java.io.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.operations.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binaryfunction.bin"))) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
            System.out.println(function);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:\n");
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory);
            System.out.println(operator.derive(function).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}