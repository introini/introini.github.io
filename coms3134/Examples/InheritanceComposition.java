// Inheritance
public class MyStack extends LinkedList {
  
  void push(Integer x){
    add(size()-1,x)
  }

  public static void main(String[] args) {
    LinkedList l;
    l = new MyStack();
  }
}


// Composition - when you want certain functionality from the inherited class
public class MyStack {
  
  LinkedList theList;
  public MyStack() {
    theList = new LinkedList();
  }

  void push(Integer x){
    theList.add(theList.size()-1,x)
  }

  public static void main(String[] args) {
    LinkedList l;
    l = new MyStack();
  }
}