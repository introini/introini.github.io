public class Problem2 {



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


  TwoStackQueue<String> q = new TwoStackQueue<String>();
  System.out.println(q.size());

  q.enqueue("I");
  q.enqueue("Am");
  q.enqueue("Sam");
  q.enqueue("No");
  System.out.println(q.dequeue());
  System.out.println(q.size());
  q.enqueue("For");
  q.enqueue("Real");
  q.enqueue("I");
  q.enqueue("Really");
  System.out.println(q.dequeue());
  q.enqueue("Am!");

  while(!q.isEmpty())
      System.out.println(q.dequeue());
    }





}