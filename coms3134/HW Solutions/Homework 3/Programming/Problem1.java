
public class Problem1 {
  public static void main(String[] args) {
    ExpressionTree tree = new ExpressionTree("5 3 + 12 * 3 /");
    System.out.println(tree);
    System.out.println(tree.eval());
    System.out.println(tree.postfix());
    System.out.println(tree.prefix());
    System.out.println(tree.infix());

    ExpressionTreeBrackets treeBrackets = new ExpressionTreeBrackets("5 3 + 12 * 3 /");
    System.out.println(treeBrackets);
    System.out.println(treeBrackets.eval());
    System.out.println(treeBrackets.postfix());
    System.out.println(treeBrackets.prefix());
    System.out.println(treeBrackets.infix());
  }
}
