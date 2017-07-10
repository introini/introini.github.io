/* Michael Introini
 * mbi2105
 * Problem2.java - Builds an array of Rectangle objects, displays the array in
 * sorted order, and then searches the array for a pre-defined value using a
 * Binay Search algorithm.
 */

import java.util.Arrays;
import java.util.Collections;

public class Problem2 {

  // Binary Search method that calls the helper method
  public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[]
  a, AnyType x) {
    return binarySearch(a,x,0,a.length - 1);
  }

  // Helper Binary Search Method
  // THis method allows us to take in some extra parameters needed for the
  // search.
  public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[]
  a, AnyType x, int start, int stop) {

    // Variable for our midpoint
    int mid;

    if (start <= stop) {
      mid = (start + stop) / 2;
      // Base Case --  Check the midpoint and return the position of the array
      // if the search term matches.
      if (a[mid].compareTo(x) == 0) {
        return mid;
      } else if (a[mid].compareTo(x) > 0) { // Search the first half
        return binarySearch(a,x,start,mid - 1);
      } else {
        return binarySearch(a,x,mid + 1,stop); // Search the last half
      }
    }
    // Return -1 if nothing is found
    return -1;
  }

  public static void main(String[] args){
    // Create + Initialize a new array of Rectangle objects
    Rectangle[] rectangles = new Rectangle[4];

    // Assign each array element a new Rectangle
    rectangles[0] = new Rectangle(12,20);
    rectangles[1] = new Rectangle(20,19);
    rectangles[2] = new Rectangle(13,26);
    rectangles[3] = new Rectangle(14,23);

    // Sort the array by perimeter in descending order.
    Arrays.sort(rectangles, Collections.<Rectangle>reverseOrder());

    // Print out the sorted array
    for (int i = 0; i < rectangles.length; i++) {
        System.out.println(rectangles[i]);
    }

    // Instantiate an object of type Rectangle for our search
    Rectangle query = new Rectangle(26,13);

    // Run the search and print out the result.
    System.out.println(binarySearch(rectangles, query));
  }
}
