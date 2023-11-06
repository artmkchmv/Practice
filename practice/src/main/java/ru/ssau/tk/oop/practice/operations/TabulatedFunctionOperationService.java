package ru.ssau.tk.oop.practice.operations;

import ru.ssau.tk.oop.practice.functions.*;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] asPointsArr = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            asPointsArr[i] = point;
            i++;
        }
        return asPointsArr;
    }
}