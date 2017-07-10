/**
 * KBestCounter - Finds the k-largest values in a set of data
 * By 3134 Staff
 */

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<? super T>>  {

  private PriorityQueue<T> heap;
  private int k;

  public KBestCounter(int k) {
    this.k = k;
    heap = new PriorityQueue<>(k);
  }


  public void count(T x) {
    if (heap.size() < k) {
      heap.offer(x);
    } else {
      if (heap.peek().compareTo(x) < 0) {
        heap.poll();
        heap.offer(x);
      }
    }
  }

  public List<T> kbest() {
    LinkedList<T> result = new LinkedList<>();
    // constant time copy of old heap
    PriorityQueue<T> newHeap = new PriorityQueue<T>(heap);
    while (!heap.isEmpty()) {
      T next = heap.remove();
      result.addFirst(next);
    }
    heap = newHeap;
    return result;
  }
}