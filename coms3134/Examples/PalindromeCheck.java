import java.util.LinkedList;
public class PalindromeCheck {
  public static boolean isPalindrome(String s) {
    LinkedList<Character> stack = new LinkedList<>();

    for (int i = 0; i < s.length()/2; i++ ) {
      stack.push(s.charAt(i)); 
    }

    int mid;

    if (s.length() % 2 == 0) {
      mid = s.length()/2;
    } else {
      mid = s.length()/2 + 1;
    }

    for (int i = mid; i < s.length(); i++) {
      if (!(stack.pop().equals(s.charAt(i)))) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(isPalindrome("{[()]}"));
  }
}