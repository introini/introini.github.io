public class TwoStackQueueTesterTwo {



    /** If implemented correctly, this code should output: 
     *  0
     *  Peter
     *  2
     *  Paul
     *  Mary
     *  Simon
     *  Alvin
     *  Theodore
     */
    public static final void main(String[] args) {


  TwoStackQueue<Integer> q = new TwoStackQueue<Integer>();
  System.out.println("Queue Size: " + q.size());
  q.enqueue(1);
  q.enqueue(2);
  q.enqueue(3);
  q.enqueue(4);
  System.out.println("Dequeue: " + q.dequeue());
  System.out.println("Queue Size: " + q.size());
  q.enqueue(5);
  q.enqueue(6);
  q.enqueue(7);
  q.enqueue(8);
  System.out.println("Dequeue: " + q.dequeue());
  q.enqueue(9);

  while(!q.isEmpty())
      System.out.println("Dequeue: " + q.dequeue());
    }





}