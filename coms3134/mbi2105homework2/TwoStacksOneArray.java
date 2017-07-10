rpublic class TwoStacksOneArray<AnyType> {
  int max;
  AnyType[] theStack; 
  int top1;
  int top2;
  int size1;
  int size2;
  int startFrom; // variable to keep track of where the Top of the stack is

  // Constructor 
  public TwoStacksOneArray(int s) {
    max = s;
    theStack = (AnyType[]) new Object[max];
    top1 = -1;
    top2 = -1;
    size1 = 0;
    size2 = 0;
    startFrom = top1 + 1; // top of the stack
  }

  // Check each stack individually for "Emptiness"
  public boolean isEmpty1() {
    return (top1 == -1);
  }
  public boolean isEmpty2() {
    return (top2 == -1);
  }

  // Check each stack's size
  public int size1() {
    return size1;
  }
  public int size2() {
    return size2;
  }

  // Used to look at the top of each stack
  public AnyType peek1() {
    return theStack[top1];
  }
  public AnyType peek2() {
    return theStack[startFrom+top2];
  }

  // Push onto the stack if we haven't reached the max size yet
  public void push1(AnyType x){
    // Ensure there is enough room in the stack to proceed
    if (top1 + 1 < max) {
    // If the sum of the two stack sizes is greater than max, prevent overflow
      if (this.size1() + this.size2() >= max) {
        System.out.println("Overflow 1");
      } else {
        top1++; // increment top of the stack
        theStack[top1] = x; // add element to stack 
        size1++; // increment the value of the size for the stack
      }
    }
  }
  // Push onto stack 2 if we haven't reached the max size yet
  public void push2(AnyType x){
    
    // Ensure there is enough room in the stack to proceed
    if (top2 + 1 < max) {
    // If the sum of the two stack sizes is greater than max, prevent overflow
      if (this.size1() + this.size2() >= max) {
        System.out.println("Overflow 2");
      } else {
        top2++; // increment top of stack 2
        theStack[startFrom+top2] = x; // add element to stack 
        size2++; // increment the value of the size for "stack 2"
      }
    }
  }

  // Pop from the stack if we have elements to pop off
  public AnyType pop1(){
    // Check that top still has elements
    if (top1 >= 0) {
      size1--;
      return theStack[top1--]; // Return the element and decrement
    } 
    return (AnyType)"Nothing to return";
  }

  public AnyType pop2(){
    // Check that top still has elements
    if (top2 >= 0) {
      size2--;
      return theStack[startFrom+top2--]; // Return the element and decrement
    } 
    return (AnyType)"Nothing to return";
  }
}
