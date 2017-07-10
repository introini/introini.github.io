import java.util.Arrays;
public class Sorting {
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType [] a) {
		int j;
		for (int p = 1; p < a.length ; p++) {
			AnyType tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j-1]) < 0; j--) {
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void selectionSort(AnyType [] a) {
		for(int p = 0; p < a.length-1; p++) {
			int minPos = p;

			for(int j = p+1; j< a.length; j++) {
				if (a[j].compareTo(a[minPos]) < 0){
					minPos = j;
				}
			}

			AnyType tmp = a[p];
			a[p] = a[minPos];
			a[minPos] = tmp;
		}
	}

	public static void main(String[] args) {
		Integer[] arr =  {1,9,4,2,8,7,3,5,0};
		
		for (int i = 1; i < arr.length/2+1; i *= 2 ) {
			for (int j = i; j < arr.length ; j += i*2) {
				System.out.println(arr[j]);
			}	
		}

	}
}

