/* Michael Introini
 * mbi2105
 * Problem215.java - An efficient algorithm to determine if there exists an 
 * integer i such that Ai = i in an array of integers A1 < A2 < A3 < . . . < AN.
 */

import java.util.Arrays;

public class Problem215 {

  public static int bSearch() {
    // Initialize an array of ints
    int arr[] = {-302,0,1,3,4,-23,6,234};
    // Sort the array
    Arrays.sort(arr);

    // Variable for setting the boundaries of the array
    int start = 0;
    int end = arr.length - 1;
    int mid;

    // Print the array to identify each position (visual aid)
    for(int i:arr){
      System.out.println(i);
    }

    // Search loop
    while (start <= end) {
      mid = (end + start) / 2;

      if (arr[mid] < mid) {
        start = mid + 1; // Ai might be in the upper half since Ai < i
      } else if (arr[mid] > mid) {
        end = mid - 1; // Ai might be in the lower half since Ai > i
      } else {
        return mid; // Found where Ai = i
      }
    }
    return -314159; // Not found
  }

  public static void main(String[] args) {
    System.out.print("Result: " + bSearch());
 }
}
