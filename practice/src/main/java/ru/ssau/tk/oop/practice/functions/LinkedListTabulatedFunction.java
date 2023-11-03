package ru.ssau.tk.oop.practice.functions;

import java.util.Iterator;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    protected static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "; " + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            return ((this.getClass() == o.getClass()) && (x == ((LinkedListTabulatedFunction.Node) o).x) && (y == ((LinkedListTabulatedFunction.Node) o).y));
        }

        @Override
        public int hashCode() {
            int total = 31;
            long new_x = Double.doubleToLongBits(x);
            long new_y = Double.doubleToLongBits(y);
            total = 31 * total + (int) (new_x ^ (new_x >>> 32));
            total = 31 * total + (int) (new_y ^ (new_y >>> 32));
            return total;
        }

        @Override
        public Object clone() {
            Node tempNode = new Node(x, y);
            tempNode.prev = this.prev;
            tempNode.next = this.next;
            return tempNode;
        }
    }

    protected int count;
    private Node head;

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2)
            throw new IllegalArgumentException("table length is less than the required minimum");
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2)
            throw new IllegalArgumentException("table length is less than the required minimum");
        if (xTo - xFrom > 1e-9) {
            double epsilon = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xFrom + i * epsilon), source.apply(xFrom + i * epsilon));
            }
        } else if (xFrom - xTo > 1e-9) {
            double epsilon = (xFrom - xTo) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xTo + i * epsilon), source.apply(xTo + i * epsilon));
            }
        } else {
            for (int i = 0; i > count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("incorrect index value");
        if (index == 0) {
            return head;
        } else {
            Node tempNode = head;
            while (index != 0) {
                tempNode = tempNode.next;
                index--;
            }
            return tempNode;
        }
    }

    public double getX(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("incorrect index value");
        return getNode(index).x;
    }

    public double getY(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("incorrect index value");
        return getNode(index).y;
    }

    public void setY(int index, double y) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("incorrect index value");
        this.getNode(index).y = y;
    }

    public int indexOfX(double x) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.x != x && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.x - x == 1e-9) {
                return index;
            } else
                return -1;
        } else
            return index;
    }

    public int indexOfY(double y) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.y != y && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.y == y) {
                return index;
            } else
                return -1;
        } else
            return index;
    }

    public int floorIndexOfX(double x) {
        if (x < leftBound())
            throw new IllegalArgumentException("x is less than the left bound");
        if (head.x - x > 1e-9)
            return 0;
        else if (head.prev.x - x < 1e-9)
            return count - 1;
        else {
            int index = 0;
            Node tempNode = head;
            while (true) {
                if (tempNode.x - x == 1e-9)
                    return index;
                else if (tempNode.x - x > 1e-9)
                    return (index - 1);
                tempNode = tempNode.next;
                index++;
            }
        }
    }

    protected double extrapolateLeft(double x) {
        if (head.prev == head)
            return head.y;
        else
            return (head.y + ((head.next.y - head.y) / (head.next.x - head.x)) * (x - head.x));
    }

    protected double extrapolateRight(double x) {
        if (head.prev == head)
            return head.y;
        else
            return (head.prev.prev.y + ((head.prev.y - head.prev.prev.y) / (head.prev.x - head.prev.prev.x)) * (x - head.prev.prev.x));
    }

    protected double interpolate(double x, int floorIndex) {
        double rightX = getX(floorIndex);
        double leftX = getX(floorIndex - 1);
        double rightY = getY(floorIndex);
        double leftY = getY(floorIndex - 1);
        return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }

    @Override
    public double apply(double x) {
        if (head.x - x > 1e-9) {
            return extrapolateLeft(x);
        } else if (x - head.prev.x > 1e-9) {
            return extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        Node tempNode = head;
        while (tempNode != head.prev) {
            result += "(" + tempNode.x + "; " + tempNode.y + ")" + ", ";
            tempNode = tempNode.next;
        }
        result += "(" + head.prev.x + "; " + head.prev.y + ")";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        LinkedListTabulatedFunction new_list = (LinkedListTabulatedFunction) o;
        if (this.count != new_list.count)
            return false;
        Node tempNode_1 = head;
        Node tempNode_2 = new_list.head;
        for (int i = 0; i < count; i++) {
            if (tempNode_1.x != tempNode_2.x || tempNode_1.y != tempNode_2.y)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int total = 31;
        Node tempNode = head;
        while (tempNode != head.prev) {
            total = 31 * total + tempNode.hashCode();
            tempNode = tempNode.next;
        }
        total = 31 * total + head.prev.hashCode();
        return total;
    }

    @Override
    public Object clone() {
        double[] xValues = new double[this.count];
        double[] yValues = new double[this.count];
        int i = 0;
        Node tempNode = head;
        while (tempNode != head.prev) {
            xValues[i] = tempNode.x;
            yValues[i] = tempNode.y;
            tempNode = tempNode.next;
            i++;
        }
        xValues[this.count - 1] = head.prev.x;
        yValues[this.count - 1] = head.prev.y;
        LinkedListTabulatedFunction tempList = new LinkedListTabulatedFunction(xValues, yValues);
        return tempList;
    }

    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }
}