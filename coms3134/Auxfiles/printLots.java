  public static void printLots(List<AnyTpe> l, List<Integer> p) {
    
    Iterator<AnyTpe> itrP = p.iterator();
    boolean err = false;

    // Check if any values of P are larger than the number of elements in L
    for (Integer itemP : p) {
      if (itemP > l.size()) {
        err = true; // Set the error flag
      }
    }

    // Check the error flag, and proceed if it wasn't set to true.
    if (!err) {
      int index = 0; // Used to indicate the index
      for (AnyTpe itemL : l) {
        // as the index increases, if p has a value that matches it gets printed
        if (p.contains(index)) {  
          System.out.println(itemL);
        } 
        index++; // Increase the index
      }
    } else {
      System.out.println("An error occured."); // Error message
    }
  }