import java.util.ArrayList;

public class ForTest {
  public static final void main(String[] args) {
    ArrayList<Integer> a = new ArrayList<Integer>();
    a.add(0);
    a.add(1);
    a.add(2);
    a.add(3);

    for(Integer i: a) {
      System.out.println(i);
    }

    System.out.println("-----------------------");
    java.util.Iterator<Integer> n = a.iterator();
    while(n.hasNext()) {
      Integer i = n.next();
      System.out.println(i);
    }

  }
}
