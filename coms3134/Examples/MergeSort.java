import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Data Structures in Java 
 * COMS W3134, Columbia University - Fall 2016
 * 
 * Merge Sort implementation.
 * Adapted from Weiss, Data Structures and Algorithm Analysis in Java. 3rd ed. 
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/Sort.java
 */
public class MergeSort {

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
  private static <T extends Comparable<? super T>> void merge(T[] a, 
     T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos].compareTo(a[rightPos]) <= 0) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }

    while (leftPos <= leftEnd) { // Copy remainder of first half
      tmpArray[tmpPos++] = a[leftPos++];
    }

    while (rightPos <= rightEnd) { // Copy remainder of right half
      tmpArray[tmpPos++] = a[rightPos++];
    }

    // Copy tmpArray back
    //for (int i = 0; i < numElements; i++, rightEnd--) {
    //  a[rightEnd] = tmpArray[rightEnd];
    //}
    for (int i = rightEnd; i>(rightEnd-numElements); i--) {
      a[i] = tmpArray[i];
    }
  }

  /**
   * Internal method that makes recursive calls. This is part of the MergeSort
   * algorithm from from Weiss, Data Structures and Algorithm Analysis in Java,
   * as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param left
   *          the left-most index of the subarray.
   * @param right
   *          the right-most index of the subarray.
   */
  private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      mergeSort(a, tmpArray, left, center);
      mergeSort(a, tmpArray, center + 1, right);
      merge(a, tmpArray, left, center + 1, right);
    }
  }

  /**
   * Merge Sort algorithm. This is the Merge Sort algorithm from from Weiss,
   * Data Structures and Algorithm Analysis in Java, as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   */
  public static <T extends Comparable<? super T>> void mergeSort(T[] a) {

    T[] tmpArray = a.clone(); // Hack to create a new array of type T
    for (int i =0; i< tmpArray.length; i++)
        tmpArray[i] = null; 

    mergeSort(a, tmpArray, 0, a.length - 1);
  }

  public static void main(String[] args) {
    Integer[] a = { 1, 4, 9, 131, 0, 2, 7, 19, 245, 18 };
    MergeSort.mergeSort(a);
    System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
  }
}
