package LR2.functions;

class Node {
    public Node next;
    public Node prev;
    double x;
    double y;
    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class LinkedListTabulatedFunction {
    protected int count;
    private Node head;
    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        }
        else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }
    LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }
    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xTo > xFrom) {
            double epsilon = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xFrom + i * epsilon), source.apply(xFrom + i * epsilon));
            }
        }
        else if (xFrom > xTo) {
            double epsilon = (xFrom - xTo) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xTo + i * epsilon), source.apply(xTo + i * epsilon));
            }
        }
        else {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
    }
    int getCount() {
        return count;
    }
    double leftBound() {
        return head.x;
    }
    double rightBound() {
        return head.prev.x;
    }
    private Node getNode(int index) {
        if (index == 0) {
            return head;
        }
        else {
            Node tempNode = head;
            while (index != 0) {
                tempNode = tempNode.next;
                index--;
            }
            return tempNode;
        }
    }
    double getX(int index) {
        return getNode(index).x;
    }
    double getY(int index) {
        return getNode(index).y;
    }
    void setY(int index, double y) {
        this.getNode(index).y = y;
    }
    int indexOfX(double x) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.x != x && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.x == x) {
                return index;
            }
            else
                return -1;
        }
        else
            return index;
    }
    int indexOfY(double y) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.y != y && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.y == y) {
                return index;
            }
            else
                return -1;
        }
        else
            return index;
    }
    int floorIndexOfX(double x) {
        int index = 0;
        if (head.x > x)
            return 0;
        if (head.prev.x < x)
            return count;
        boolean state = head.x <= x && head.prev.x >= x;
        if (state) {
            Node tempNode = head;
            while (tempNode.x != x && tempNode != head.prev) {
                tempNode = tempNode.next;
                index++;
            }
            return index;
        }
        if (state) {
            Node tempNode = head;
            while (tempNode.x < x && tempNode != head.prev) {
                tempNode = tempNode.next;
                index++;
            }
            return (index - 1);
        }
        return 0;
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
        if (head.prev == head)
            return head.y;
        else {
            double rightX = getX(floorIndex);
            double leftX = getX(floorIndex - 1);
            double rightY = getY(floorIndex);
            double leftY = getY(floorIndex - 1);
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
        }
    }
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (head.prev == head)
            return head.y;
        else
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }
}