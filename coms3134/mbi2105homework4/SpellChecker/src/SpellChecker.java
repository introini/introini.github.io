import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Michael Introini
 * mbi2105
 *

 Implement a spelling checker by using a hash table. Assume that the dictionary
 comes from an existing large dictionary. Output all misspelled words and
 the line numbers in which
 they occur. Also, for each misspelled word, list any words in the dictionary that are
 obtainable by applying any of the following rules:

 a. Add one character.
 b. Remove one character.
 c. Exchange adjacent characters.

 *
 */

public class SpellChecker {

  private static HashSet<String> dictionary(String f) throws IOException {
    // Variable Declaration
    BufferedReader br = new BufferedReader(new FileReader(f));
    String word;
    HashSet<String> dictionary = new HashSet<>();

    // Read in the file and store each line in the hashmap
    while ((word = br.readLine()) != null) {
      // Assuming the dictionary provided is "1 Line = 1 Word" we can read
      // each line into the "dictionary" hashset without splitting.
      // The actual word is the Key, and it's hash is the Value
      dictionary.add(word.toLowerCase());
    }

    // Close the buffer reader
    br.close();

    return dictionary;
  }

private static HashSet<String> suggestedWords (String s, HashSet<String> d) {
  // Variable Declaration
  HashSet<String> suggestedWords = new HashSet<>();

    // Add One Character
    for (int i = 0; i < s.length()+1; i++) {

      for (int j = 97; j < 123; j++) {
        StringBuilder sb = new StringBuilder(s);
        String oneMore = sb.insert(i,(char)j).toString();

        // Check the current word for equality against the list of words of the same length
        if (d.contains(oneMore)){

          suggestedWords.add(oneMore);

        }

      }

    }

    // Remove One Character
    for (int i = 0; i < s.length(); i++) {
      StringBuilder sb = new StringBuilder(s);
      String oneLess = sb.deleteCharAt(i).toString();

      // Check the current word for equality against the list of words of the same length
      if (d.contains(oneLess)){

        suggestedWords.add(oneLess);

      }

    }

    // Exchange Adjecent characters by spliting to array and using the element swap algorithm
    // After each swap, check the dictionary and then return to the original order for the next iteration
    char[] c = s.toCharArray();
    for (int i=0; i<c.length-1; i++) {
      char tmp = c[i];
      c[i] = c[i+1];
      c[i+1] = tmp;

      // Convert back to string and check the dictionary for
      String str = new String(c);
      if (d.contains(str)) {

          suggestedWords.add(str);

        }

      c[i+1]= c[i];
      c[i] = tmp;
    }

  return suggestedWords;
}

  private static String stripPunctuation(String s) {

    String stripLeading;
    String stripTrailing;

    // Strip anything that isn't a letter or number from the string.
    stripLeading = s.replaceFirst("^[^a-z0-9]+","");

    // Strip anything that isn't a letter from the end of the string we just
    // worked with.
    stripTrailing = stripLeading.replaceAll("[^a-z0-9]+$", "");

    return stripTrailing;
  }


  public static void main (String[] args) {

    String file = args[0];
    String checkMe = args[1];

    try {
    // Variable Declaration
      BufferedReader br = new BufferedReader(new FileReader(checkMe));
      String line;
      HashSet<String> dictionary = dictionary(file);
      List<String> misspelledWords = new LinkedList<>();
      int lineNum = 0;

      // Loop through each line of the file
      while ((line = br.readLine()) != null) {
        // Collect the line number
        lineNum++;

        // Loop through each word and check the dictionary for it's existence
        for(String s: line.split("\\s")) {

          String lower = stripPunctuation(s.toLowerCase());

          // Check the dictionary
          if (!dictionary.contains(lower) && !lower.matches("\\d")){

            System.out.println("Misspelled at Line " + lineNum + " - " + lower);

            System.out.println("Suggested Words: ");

              // Run each word throught the Suggestion method
              for (String suggested : suggestedWords(lower, dictionary)) {

                System.out.println(suggested);

              }

            System.out.println("");

          }

          } //  End For Loop (words)

        } // End While Loop

        br.close();

    } catch (IOException e) {

      System.out.println(e);

    }
  }

}
