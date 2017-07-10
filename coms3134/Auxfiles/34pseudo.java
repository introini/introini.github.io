  public static void getIntersection(List a, List b) {

    // List that will holw the intersection of A and B
    List<T> c = new List<>();

    // Linear Search through A to find elemnts from B.
    for (T itemB : b) {
      for (int i = 0; i<a.size(); i++) {
        // If a match is found add it to list C
        if (a.get(i) == itemB) {
          c.add(a.get(i));
        } 
      }
    }

    // Print out the Intersection of A and B, unless it's empty.
    if (!c.isEmpty()) {
      for (T itemC : c) {
        System.out.println(itemC);
      }
    }
  }