/* Michael Introini
 * mbi2105
 * Build a queue out of two completely separate stacks, S1 and S2. Enqueue 
 * operations happen by pushing the data on to stack 1. Dequeue operations 
 * are completed with a pop from stack 2.
*/

public class TwoStackQueue<AnyType> implements MyQueue<AnyType> {
  private MyStack<AnyType> stack1;
  private MyStack<AnyType> stack2;

  public TwoStackQueue() {
    stack1 = new MyStack<>(); // Enqueue List
    stack2 = new MyStack<>(); // Dequeue List
  }

  public void enqueue(AnyType x) {
    stack1.push(x);
  }

  public AnyType dequeue() {
    // Reverse the stack to get the last element first
    reverse(stack1, stack2);
    // Pop the element and save to variable
    AnyType data = stack2.pop();
    // Reverse the stack again so that stack 1 pushes in the same order as 
    // before.
    reverse(stack2, stack1);
    return data;
  }

  public boolean isEmpty() {
    // Queue is empty only if both stacks are empty
    return (stack1.isEmpty() && stack2.isEmpty());
  }

  public int size() {
    // Size of the queue is the sum of both stacks
    return (stack1.size() + stack2.size());
  }

  private void reverse(MyStack<AnyType> stack, MyStack<AnyType> tmpStack) {
    while(!stack.isEmpty()) {
      // Save the top of the stack in temp
      AnyType temp = stack.peek();
      // Push the temp data to the front of the "other" stack.
      tmpStack.push(temp);
      // Remove the first element from the original stack
      stack.pop();
    }
  }
}