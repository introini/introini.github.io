public class BinarySearchGenerics {
  public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[]
  a, AnyType x) {
    return binarySearch(a,x,0,a.length - 1);
  }

  public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[]
  a, AnyType x, int start, int stop) {

    int mid;

    // Base Case
    if (start <= stop) {
      mid = (start + stop) / 2;

      if (a[mid].compareTo(x) == 0) {
        return mid;
      } else if (a[mid].compareTo(x) > 0) {
        return binarySearch(a,x,start,mid - 1);
      } else {
        return binarySearch(a,x,mid + 1,stop);
      }
    }
    return -1;
  }


  public static void main (String[] args) {
    String[] letters = {"A","B","C","D","E","F","G"};
    System.out.println(binarySearch(letters,"G"));
  }
}
