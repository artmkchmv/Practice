package LR2.functions;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    protected int count;
    ArrayTabulatedFunction(double[] xValues, double[] yValues ){
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }
    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        xValues = new double[count];
        yValues = new double[count];
        double epsilon;
        if(xTo - xFrom > pow(10,-9))
        {
            epsilon = (xTo - xFrom)/(count-1);
            for(int i=0; i<count;i++)
            {
                xValues[i]=xFrom + i*epsilon;
                yValues[i]=source.apply(xFrom + i*epsilon);
            }
        }
        else if(xFrom - xTo > pow(10,-9))
        {

            epsilon = (xFrom - xTo)/(count-1);
            for(int i=0; i<count;i++)
            {
                xValues[i]=xFrom + i*epsilon;
                yValues[i]=source.apply(xFrom + i*epsilon);
            }
        }
        else {
            for(int i=0; i<count;i++) {
                xValues[i] = xFrom;
                yValues[i] = source.apply(xFrom);
            }
        }
    }
    public int getCount()
    {
        return count;
    }
    public double getX(int index){
        return xValues[index];
    }
    public double getY(int index){
        return yValues[index];
    }
    public void setY(int index, double val){
        yValues[index]= val;
    }
    public double leftBound(){
        return xValues[0];
    }
    public double rightBound(){
        return xValues[count-1];
    }
    public int indexOfX(double x) {
        int i = 0;
        while (i < count)
        {
            if(abs(xValues[i] - x) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }
    public int indexOfY(double y) {
        int i = 0;
        while (i < count)
        {
            if(abs(yValues[i] - y) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        int i = 0;
        if (xValues[i] - x > 1e-9)
            return 0;
        else if (x - xValues[count-1] > 1e-9)
            return count - 1;
        else {
            while(true) {
                if (abs(xValues[i] - x) < 1e-9)
                    return i;
                else if (xValues[i] - x > 1e-9)
                    return i - 1;
                i++;
            }
        }
    }
    protected double extrapolateLeft(double x) {
        if (count==1)
            return yValues[0];
        else
            return (yValues[0] + ((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0]));
    }
    protected double extrapolateRight(double x) {
        if (count==1)
            return yValues[0];
        else
            return (yValues[count-2] + ((yValues[count-1] - yValues[count-2]) / (xValues[count-1] - xValues[count-2])) * (x - xValues[count-2]));
    }
    protected double interpolate(double x, int floorIndex) {
        if (count==1)
            return yValues[0];
        else {
            double rightX = getX(floorIndex);
            double leftX = getX(floorIndex - 1);
            double rightY = getY(floorIndex);
            double leftY = getY(floorIndex - 1);
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
        }
    }
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (count==1)
            return yValues[0];
        else
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }

    @Override
    public double apply(double x) {
        if(xValues[0]-x>1e-9){
            return extrapolateLeft(x);
        }
        else if(x - xValues[count-1]>1e-9){
            return extrapolateRight(x);
        }
        else{
            if(indexOfX(x)!= -1){
                return getY(indexOfX(x));
            }
            else{
                return interpolate(x, floorIndexOfX(x));
            }
        }

    }
}