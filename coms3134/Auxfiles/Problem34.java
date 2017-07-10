import java.util.ArrayList;
import java.util.Iterator;
public class Problem34<AnyTpe> {
  public static void printLots(ArrayList<Integer> l, ArrayList<Integer> p) {

    // List that will holw the intersection of A and B
    ArrayList<Integer> c = new ArrayList<>();

    Iterator<Integer> itrL = l.iterator();
    Iterator<Integer> itrP = p.iterator();
    Iterator<Integer> itrC = c.iterator();

    while (itrL.hasNext() && itrP.hasNext())  {
      int ll = itrL.next();
      int pp = itrP.next();

      if (ll < pp) {
        itrL.next();
      } else if (ll > pp) {
        itrP.next();
      } else {
        c.add(itrL.next());
      }
    }
    System.out.println(c.size());
    
  }

  public static void main(String[] args) {
    ArrayList<Integer> listL = new ArrayList<>();
    ArrayList<Integer> listP = new ArrayList<>();

    listL.add(1);
    listL.add(2);
    listL.add(2);
    listL.add(3);

    listP.add(2);
    listP.add(3);
    listP.add(5);

    printLots(listL, listP);
  }
}
