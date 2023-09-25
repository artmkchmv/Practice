package myfirstpackage;

public class myfirstpackage {

  private int first_parameter;
  private int second_parameter;

  public myfirstpackage() {
    first_parameter = 1;
    second_parameter = 1;
  }

  public int get_first_parameter() {
    return first_parameter;
  }

  public int get_second_parameter() {
    return second_parameter;
  }

 public void set_first_parameter(int value) {
    first_parameter = value;
  }

 public void set_second_parameter(int value) {
    second_parameter = value;
  }

 public int divide() {
    if (second_parameter == 0) {
      System.out.println("Error: dividing by zero!");
      return 0;
    }
    return first_parameter / second_parameter;
  }
}