class MySecondClass {

  private int first_parameter;
  private int second_parameter;

  MySecondClass() {
    first_parameter = 1;
    second_parameter = 1;
  }

  int get_first_parameter() {
    return first_parameter;
  }

  int get_second_parameter() {
    return second_parameter;
  }

  void set_first_parameter(int value) {
    first_parameter = value;
  }

  void set_second_parameter(int value) {
    second_parameter = value;
  }

  int divide() {
    if (second_parameter == 0) {
      System.out.println("Error: dividing by zero!");
      return 0;
    }
    return first_parameter / second_parameter;
  }
}

class MyFirstClass {

  public static void main(String[] args) {
    MySecondClass o = new MySecondClass();
    System.out.println(o.divide());
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j <= 8; j++) {
        o.set_first_parameter(i);
        o.set_second_parameter(j);
        System.out.print(o.divide());
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}