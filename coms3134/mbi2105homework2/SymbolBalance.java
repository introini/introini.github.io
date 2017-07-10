/* Michael Introini
 * mbi2105
-Make an empty stack. 
-Read characters until end of file. 
-If the character is an opening symbol, push it onto the stack. 
-If it is a closing symbol, then if the stack is empty report an error. 
Otherwise, pop the stack. 
-If the symbol popped is not the corresponding opening symbol, then report an 
error. 
-At end of file, if the stack is not empty report an error
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SymbolBalance<T> {

  private static MyStack<Character> st;

  public SymbolBalance() {  
   st = new MyStack<>();
  }

  private static boolean isOpenSymbol(Character c) {
    return (c == '{' || c == '(' || c == '[') ? true : false;
  }
  
  private static boolean isCloseSymbol(Character c) {
    return (c == '}' || c == ')' || c == ']') ? true : false;
  }

  public static void readFile(String f) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(f));
    StringBuilder sb = new StringBuilder(); // Stores the contents of the file
    String line; // Stores each line of the file before being stored in sb
    boolean commentBlock = false; // Flag Comment Block
    boolean stringLiteral = false; // Flag String Literal
    char dquote = 34; // Double Quote ascii for comparison 

    // Read in the file and store each line in a String Builder variable
    while((line = br.readLine()) != null) {
        sb.append(line);
    }
    br.close();

    // Check each character of the stringbuilder object for symbol balancing
    for (int i =0; i < sb.length(); i++) {
    
    // Find the beginning of a comment block
    // If /* is detected, set commentBlock to true
    // The commentBlock boolean allows for symbols within comment blocks to be 
    // ignored.
      if (sb.charAt(i) == '/') {
        if (sb.charAt(++i) == '*') {
            commentBlock = true;
          }
        }
    
    // Set commentBlock to false if a terminating */ is found
      if (commentBlock) {
        if (sb.charAt(i) == '*') {
          if (sb.charAt(++i) == '/') {
              commentBlock = false;
          }
        }
      }

    // If we're not in a comment block, gather all symbols for processing
    if (!commentBlock) {

    // Check if the next character is an Open Symbol {, (, [
        if (isOpenSymbol(sb.charAt(i))) {
    
    // Push onto the stack
          st.push(sb.charAt(i));
    
    // Check if the next symbol is closing symbol
        } else if (isCloseSymbol(sb.charAt(i))) {
    
    // If stack is empty, print error and break out.
          if (st.isEmpty()) {    
            System.out.println("Unbalanced! Symbol " + sb.charAt(i) + 
              " is mismatched!");
    
    // Otherwise check if the character corresponds to the popped char from
    // the stack. If it's not the corresponding character, print error and
    // break out. 
          } else if (sb.charAt(i) == '}') {
              if (st.pop() != '{') {
                System.out.println("Unbalanced! Symbol " + sb.charAt(i) + 
                " is mismatched!");
                break;
              }
          } else if (sb.charAt(i) == ')') {
              if (st.pop() != '(') {
               System.out.println("Unbalanced! Symbol " + sb.charAt(i) + 
               " is mismatched!");
               break;
              }
          } else if (sb.charAt(i) == ']') {
              if (st.pop() != '[') {
                System.out.println("Unbalanced! Symbol " + sb.charAt(i) + 
                " is mismatched!");
                break;
              }
          } 
    // Check for String Literals      
        } else {
          if (sb.charAt(i) == dquote) {
            stringLiteral = true;
          } 
          if (stringLiteral && sb.charAt(i) == dquote) {
            stringLiteral = false;
          }
        } 
      }
    }

    // Error if string literal never closes and returns false
    if (stringLiteral) {
       System.out.println("Unbalanced! Symbol " + dquote + " is mismatched!");
    }

    // EOF and stack still has symbols in it.
    if (!st.isEmpty()) {
        System.out.println("Stack is not empty!");
    } 
  // Loop ends here
  }

  public static void main(String[] args) {
    
    SymbolBalance<Character> symbolBalance = new SymbolBalance<>();

    try {
       symbolBalance.readFile(args[0]);

    } catch (IOException e) {
      System.out.println(e);
    }

  }
}