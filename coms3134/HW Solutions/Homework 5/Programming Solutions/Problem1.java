/* Problem 1 Solution from Homework 5
 * 3134 Data Structures in Java
 */

import java.util.Arrays;

public class Problem1 {

  /**
   * Internal method that merges two sorted halves of a subarray (from Weiss
   * Data Structures and Algorithm Analysis in Java)
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param leftPos
   *          the left-most index of the subarray.
   * @param rightPos
   *          the index of the start of the second half.
   * @param rightEnd
   *          the right-most index of the subarray.
   */
  private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos] <= a[rightPos]) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }

    while (leftPos <= leftEnd) { // Copy rest of first half
      tmpArray[tmpPos++] = a[leftPos++];
    }

    while (rightPos <= rightEnd) { // Copy rest of right half
      tmpArray[tmpPos++] = a[rightPos++];
    }

    // Copy tmpArray back
    for (int i = 0; i < numElements; i++, rightEnd--) {
      a[rightEnd] = tmpArray[rightEnd];
    }
  }

  /**
   * Problem 5: Iterative Bottom-up Merge Sort
   */
  public static void mergeSortB(Integer[] inputArray) {
    int width = 1;

    Integer[] tempArray = new Integer[inputArray.length];

    while (width <= inputArray.length) {
      width *= 2;
      for (int i = 0; i < inputArray.length; i += width) {
        merge(inputArray, 
              tempArray, 
              i, 
              Math.min(i + width / 2, inputArray.length - 1),
              Math.min(i + width - 1, inputArray.length - 1));
      }
    }
    return;
  }

  public static void main(String[] args) {
    // Weiss sort
    Integer[] a = { 1, 4, 9, 131, 0, 2, 7, 19, 245, 18 };
    Integer[] b = { 2, 189, 71, 5, 0, 33, 723, 64, 11, 109 };

    Problem1.mergeSortB(a);
    System.out.println(Arrays.toString(a)); 
    // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
    Problem1.mergeSortB(b);
    System.out.println(Arrays.toString(b)); 
    // Should be [0, 2, 5, 11, 33, 64, 71, 109, 189, 723]
  }
}