import java.util.Iterator;

public class MyNewLinkedList<AnyType> extends List<AnyType> {

  // Node Inner Class//
  private static class Node<AnyType> {
    AnyType data;
    Node<AnyType> next;
    Node<AnyType> prev;

    public Node() {
      data = null;
      next = null;
    }

  }

  // BEGIN: Linked List Class
    Node<AnyType> head;
    Node<AnyType> tail;
    int size;

    public MyNewLinkedList() {
      size = 0;
      head = null;
      tail = null;
    }

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return size() == 0;
    }

    public void add(AnyType x) {
      Node<AnyType> n = new Node<>();
      n.data = x;
      n.next = tail.prev;
      n.prev = n.prev;
    }

    }

}

class test {
    public static void main(String[] args) {
    MyNewLinkedList<Integer> list = new MyNewLinkedList<>();

    int size = list.size();
    System.out.println(size);

    list.add(1);
  }

}

