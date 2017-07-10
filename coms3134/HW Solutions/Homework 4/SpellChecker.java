import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SpellChecker {
  private HashSet<String> dictionary;

  public SpellChecker(String dictionaryFile) throws IOException {
    dictionary = new HashSet<>();

    BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
    String line;

    while ((line = br.readLine()) != null) {
      line = line.toLowerCase();
      dictionary.add(line);
    }
    br.close();
    System.out.println(dictionary.size() + " words in dictionary");
  }

  public void checkFile(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line;

    int lineNumber = 0;

    while ((line = br.readLine()) != null) {
      lineNumber++;
      line = line.toLowerCase();
      String[] words = line.split("\\s+");
      for (String word : words) {
        // remove all trailing and leading punctuation
        word = word.replaceAll("[^A-z0-9]+$", "").replaceAll("^[^A-z0-9]+", "");
        if (word.length() > 0) {
          if (!dictionary.contains(word)) {
            System.out.println(String.format("Word misspelled at line #%d: %s", lineNumber, word));
            System.out.println("Suggested valid words:");
            for(String s : similarWords(word)) {
              System.out.println(s);
            }
            System.out.println();
          }
        }
      }
    }
    br.close();
  }

  public Set<String> similarWords(String word) {
    Set<String> similarWords = new HashSet<>();

    // add one character
    for (char i = 'a'; i <= 'z'; i++) {
      for (int j = 0; j < word.length() + 1; j++) {
        String newWord = word.substring(0, j) + i + word.substring(j, word.length());
        if (dictionary.contains(newWord)) {
          similarWords.add(newWord);
        }
      }
    }

    // remove one character
    for (int j = 0; j < word.length(); j++) {
      String newWord = word.substring(0, j) + word.substring(j + 1, word.length());
      if (dictionary.contains(newWord)) {
        similarWords.add(newWord);
      }
    }

    // exchange adjacent
    for (int j = 0; j < word.length() - 1; j++) {
      char[] wordChars = word.toCharArray();
      char temp = wordChars[j];
      wordChars[j] = wordChars[j + 1];
      wordChars[j + 1] = temp;
      String newWord = new String(wordChars);
      if (dictionary.contains(newWord)) {
        similarWords.add(newWord);
      }
    }

    return similarWords;
  }

  public static void main(String[] args) {
    SpellChecker spellChecker;
    try {
      spellChecker = new SpellChecker(args[0]);
      spellChecker.checkFile(args[1]);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
