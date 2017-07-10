import java.util.Stack;

public class RandomExpression {

  /**
   * Uses CFG to construct a random postfix string
   * 
   * @param minLength
   *          minimum length of postfix expression
   * @return a postfix expression
   */
  public static String getRandomExpression(int minLength) {
    String[] RULES = { "EE+", "EE-", "EE*", "EE/" };
    String[] TERMINALS = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    String expression = "E";

    do {
      StringBuilder sb = new StringBuilder();
      for (char ch : expression.toCharArray()) {
        if (ch != 'E') {
          sb.append(ch);
        } else {
          if (Math.random() < 0.1) {
            String selectedTerminal = TERMINALS[(int) (Math.random() * TERMINALS.length)];
            sb.append(selectedTerminal + " ");
          } else {
            String selectedRule = RULES[(int) (Math.random() * RULES.length)];
            sb.append(selectedRule + " ");
          }
        }
      }
      expression = sb.toString();
    } while (expression.length() < minLength);

    StringBuilder sb = new StringBuilder();

    for (char ch : expression.toCharArray()) {
      if (ch != 'E') {
        sb.append(ch);
      } else {
        String selectedTerminal = TERMINALS[(int) (Math.random() * TERMINALS.length)];
        sb.append(selectedTerminal + " ");
      }
    }

    expression = sb.toString();

    return expression;
  }

  /**
   * Stack-evaluates a postfix expression
   * 
   * @param expression
   *          postfix expression string
   * @return double result
   */
  public static double evaluate(String expression) {
    Stack<Double> stack = new Stack<Double>();
    expression = expression.replaceAll("\\s+", "");
    for (char ch : expression.toCharArray()) {
      if (Character.isDigit(ch)) {
        stack.push((double) Character.getNumericValue(ch));
      } else {

        double right = stack.pop();
        double left = stack.pop();

        if (ch == '+') {
          stack.push(left + right);
        } else if (ch == '-') {
          stack.push(left - right);
        } else if (ch == '/') {
          stack.push(left / right);
        } else {
          stack.push(left * right);
        }
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    String expression = getRandomExpression(20);
    System.out.println(expression);
    System.out.println(evaluate(expression));
  }
}
