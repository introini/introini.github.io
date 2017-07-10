/* Michael Introini
 * mbi2105
 * Creates a LinkedLists implementation of the Stack class.
 */
import java.util.LinkedList;

public class MyStack<T> {

  private LinkedList<T> stack;
 
  public MyStack() {
    this.stack = new LinkedList<>();
  }

  public boolean isEmpty() {
    return stack.size() == 0;
  }
  public int size() {
    return stack.size();
  }
  public T peek() {
    // Returns the first element in the LinkedList.
    return (T)stack.getFirst();
  }

  public void push(T x){
    // Adds an element to the front of the LinkedList.
    stack.addFirst(x);
  }

  public T pop(){
    // Saves a copy of the first element of the LinkedList 
    T data = stack.getFirst();
    // Removed the element from the list
    stack.removeFirst();
    // Returns of the data
    return data;
  }
  
}