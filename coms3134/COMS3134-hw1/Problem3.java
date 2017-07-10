/* Michael Introini
 * mbi2105
 * Problem3.java - Implements the 3 code fragments from written problem 3. Each
 * fragment is then run within a forloop that supplies values of n for the test.
 */

public class Problem3 {
  public static void a(int n) {
    // Add sleep to allow for system to calculate runtime.
    try {
      Thread.sleep(100);
    } catch(Exception e) {
      System.out.println(e);
    }

    int sum = 0;

    for ( int i = 0; i < 23; i++) {
      for ( int j = 0; j < n ; j++) {
        sum = sum + 1;
      }
    }
  }

  public static void b(int n) {
    // Add sleep to allow for system to calculate runtime.
    try {
      Thread.sleep(100);
    } catch(Exception e) {
      System.out.println(e);
    }

    int sum = 0;

    for ( int i = 0; i < n ; i++) {
      for ( int k = i ; k < n ; k++) {
        sum = sum + 1;
      }
    }
  }

  public static int c(int n, int k) {
    // Add sleep to allow for system to calculate runtime.
    try {
      Thread.sleep(100);
    }
    catch (Exception e) {
      System.out.println(e);
    }

    if (n <= k) {
      return 1;
    } else {
      return c(n/k,k) + 1;
    }
  }

  public static void main(String[] args) {
    long start;
    long end;
    long diff;
    int n;

    // Algorithm a
    System.out.println("-------------------------A------------------------");
    n = 10;

    for (int i=1; i<=5; i++) {
      // This loop will run a total a 5 times with the numbers 10, 100, 1000,
      // 100000, and 1000000

      start = System.currentTimeMillis(); // Start Timer

      a(n); // Run algorithm a

      end = System.currentTimeMillis(); // End Timer
      diff = end - start; // Take the difference to get a runtime.

      // Print the Results
      System.out.println("Algorithm a with input " + n + " took: " + diff
      + " milis to complete!");

      // Increment N by a factor of 10 to get a new number to test with.
      n=n*10;
    } // End loop for algorithm a

    // Algorithm b
    System.out.println("-------------------------B------------------------");
    n = 10; // Resets n to 10

    for (int i=1; i<=5; i++) {
      // This loop will run a total a 5 times with the numbers 5, 25, 125, 625,
      // and 3125

      start = System.currentTimeMillis(); // Start Timer

      b(n); // Run algorithm b

      end = System.currentTimeMillis(); // End Timer
      diff = end - start; // Take the difference to get a runtime.

      // Print the Results
      System.out.println("Algorithm b with input " + n + " took: " + diff
      + " milis to complete!");

      // Increment N by a factor of 5 to get a new number to test with.
      n=n*10;
    } // End loop for algorithm b

    // Algorithm C
    System.out.println("-------------------------C------------------------");
    n = 10; // Resets n to 10

    for (int i=1; i<=5; i++) {
      // This loop will run a total a 5 times with the numbers 10, 100, 1000,
      // 100000, and 1000000

      start = System.currentTimeMillis(); // Start Timer

      c(n,2); // Run algorithm c

      end = System.currentTimeMillis(); // End Timer
      diff = end - start; // Take the difference to get a runtime.

      // Print the Results
      System.out.println("Algorithm c with input " + n + " took: " + diff
      + " milis to complete!");

      // Increment N by a factor of 10 to get a new number to test with.
      n=n*10;
    } // End loop for algorithm c


  }
}
