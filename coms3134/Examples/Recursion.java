public class Recursion {
  public static int factorial(int n) {
    // Base Case
    if (n == 0) {
      return 1;
    }
    // Tail recursion -- Trivial to write as a loop
    return n*factorial(n-1);
  }
  public static int fibbonacci(int n) {
    // Base Case
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }

    return fibbonacci(n-1) + fibbonacci(n-2);
  }

  public static final void main(String[] args) {
    int x = Integer.parseInt(args[0]);

    System.out.println(fibbonacci(x));
  }
}
