/* Michael Introini
 * mbi2105
 * Problem1.java - Creates an array of Rectangle objects and finds the largest
 * Rectangle on the basis of its perimeter.
 */

import java.util.Arrays;
import java.util.Collections;

public class Problem1 {
  // Provided routine from assignment
  public static <AnyType extends Comparable<AnyType>>  AnyType findMax(AnyType[] arr) {
    int maxIndex = 0;
    for (int i = 1; i < arr.length; i++)
      if ( arr[i].compareTo(arr[maxIndex]) > 0 )
        maxIndex = i;
      return arr[maxIndex];
  }

  public static void main(String[] args){
    // Create + Initialize a new array of Rectangle objects
    Rectangle[] rectangles = new Rectangle[4];

    // Assign each array element a new Rectangle
    rectangles[0] = new Rectangle(12,24);
    rectangles[1] = new Rectangle(16,26);
    rectangles[2] = new Rectangle(13,23);
    rectangles[3] = new Rectangle(12,18);

    // Sort the array by perimeter in descending order.
    Arrays.sort(rectangles, Collections.<Rectangle>reverseOrder());

    // Print out the list of Rectangles being compared
    for (Rectangle rectangle : rectangles) {
      System.out.println(rectangle);
    }

    // Print out the Width and Length of the Rectangle with the largest
    // perimeter.
    System.out.print("\n");
    System.out.println("Largest Perimeter: " + findMax(rectangles));
    }
}
