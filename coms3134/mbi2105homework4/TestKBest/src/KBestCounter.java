import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<? super T>> {

  private PriorityQueue<T> heap;
  private int k;

  public KBestCounter(int k) {
    heap = new PriorityQueue<T>(k);
    this.k = k;
  }

  public void count(T x) {
    // Make sure we always have 5 items or less in the heap
    if (heap.size() < k) {
      // Add value to heap
      heap.add(x);
      // Check if the next value is larger than the current kth largest
    } else if (x.compareTo(heap.peek()) > 0) {
      // Delete the root
      heap.poll();
      // Insert the new kth largest
      heap.add(x);

    }

  }

  @SuppressWarnings("unchecked")
  public List<T> kbest() {
    // List of kth largest values
    LinkedList<T> bestList = new LinkedList<T>();
    // Temp array for sorting the list
    T[] sortMe = (T []) new Comparable[k];

    // Add values from heap to the array
    for (int i = 0; i < k; i++) {
      sortMe[i] = heap.poll();
    }

    // Add values from from "back" to "front"
    for (T e : sortMe) {
      if (e != null) {
        bestList.addFirst(e);
      }
    }

    // Restore the heap with the values from sortMe
    for (T e : bestList) {
      if (e != null) {
        heap.add(e);
      }
    }

    return bestList;
  }

}
