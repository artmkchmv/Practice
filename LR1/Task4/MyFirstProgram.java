import myfirstpackage.*;

class MyFirstClass {
  public static void main(String[] args) {
    myfirstpackage o = new myfirstpackage();
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