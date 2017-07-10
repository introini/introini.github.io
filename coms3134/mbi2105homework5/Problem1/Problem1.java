/**
 * Mike Introini
 * mbi2105
 *
 * Write a non-recursive (iterative) implementation of Merge Sort.
 */
public class Problem1 {
  
  public static void mergeSortB(Integer[] inputArray){

    Integer[] tmpArr = inputArray.clone();
    int size = 1; // Subdivision size initialized to 1

    // Loop through array until subdivision size exceeds size of array
    while (size < inputArray.length) {
      // Initialize index
        int i = 0;
        // Loop through array from left to right
        // untouched if necesarry
        while (i < inputArray.length-size) {
          // Merge each sub array using pointers to the left index, right
          // index (second half) and either the right-most element of the
          // sub array, or the last element of the initial array (which ever
          // is smaller.
          merge(inputArray, tmpArr, i, i + size, Math.min(i + size*2 - 1,
              inputArray.length-1));

          // Increase the index by size*2
          i += size * 2;
        }
        // Increase the size of the subarrays by powers of 2
        size *= 2;
    }

  }


  /**
   * Internal method that merges two sorted halves of a subarray.
   * @param a an array of Comparable items.
   * @param tmpArray an array to place the merged result.
   * @param leftPos the left-most index of the subarray.
   * @param rightPos the index of the start of the second half.
   * @param rightEnd the right-most index of the subarray.
   */
  
  private static <T extends Comparable<? super T>> void merge( Integer[] a, 
    Integer[] tmpArray, int leftPos, int rightPos, int rightEnd ) { 

    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while( leftPos <= leftEnd && rightPos <= rightEnd )
      if( a[ leftPos ] <= a[ rightPos ] )
        tmpArray[ tmpPos++ ] = a[ leftPos++ ];
      else
        tmpArray[ tmpPos++ ] = a[ rightPos++ ];

    while( leftPos <= leftEnd )    // Copy rest of first half
      tmpArray[ tmpPos++ ] = a[ leftPos++ ];

    while( rightPos <= rightEnd )  // Copy rest of right half
      tmpArray[ tmpPos++ ] = a[ rightPos++ ];

    // Copy tmpArray back
    for( int i = 0; i < numElements; i++, rightEnd-- ) {
      a[rightEnd] = tmpArray[rightEnd];
    }

  }

  public static void main(String[] args) {
    Integer[] arr1 =  {4,1,14,13,3,15,4,5};
    Integer[] arr2 =  {16,10,6,19,7,9,11};

    System.out.println("Array 1: Unsorted");
    for (int i = 0 ; i < arr1.length ; i++) {
      System.out.print(arr1[i] + ", ");
    }

    mergeSortB(arr1);

    System.out.print("\n\n");
    System.out.println("Array 1: Sorted");
    for (int i = 0 ; i < arr1.length ; i++) {
      System.out.print(arr1[i] + ", ");
    }

    System.out.print("\n\n");
    System.out.println("Array 2: Unsorted");
    for (int i = 0 ; i < arr2.length ; i++) {
      System.out.print(arr2[i] + ", ");
    }
    mergeSortB(arr2);

    System.out.print("\n\n");
    System.out.println("Array 2: Sorted");
    for (int i = 0 ; i < arr2.length ; i++) {
      System.out.print(arr2[i] + ", ");
    }


  }

  }
