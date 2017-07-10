import java.util.StringTokenizer;

public class ExpressionTree {

  private ExpressionNode root;

  public ExpressionTree(String postFix) {

    // Run through the stack based algorithm to build the tree.
    // Pushing Expression NODES on to the stack. When Done, pop the stack
    // and make that the root.

    MyStack<ExpressionNode> stack = new MyStack<>();
    String[] tokens = postFix.split("\\s");
    /*

    Scan the tokens left to right
	  Push for Operand
	  Pop twice for Operator, then hang the operands off the operator and
	  push that subtree back onto the stack, keep going until you're out of elements.
		  First Operand RIGHT
		  Second Operand LEFT

     */

    for (int i = 0; i < tokens.length; i++) {

      String currentTk = tokens[i];
      if (!isOperator(currentTk)) {

        System.out.println(Integer.parseInt(currentTk));
        
        root.right.operand = Integer.parseInt(currentTk) ;
        
        stack.push(root);

      } else {

        root.right = stack.pop();
        root.left = stack.pop();
        
        root.operator =  postFix.charAt(i);

        stack.push(root);

      }

    }

    root.postFix(stack.peek());

  }


  private boolean isOperator(char s) {

    return (s == "+" || s == "-" || s == "/" || s == "*");

  }

  // public int evaluate() {
  //   // Public version of the function
  //   return evaluate(root);
  // }

  // private int evaluate(ExpressionNode t) {
  //   // Do the traversal to evaluate the tree rooted at t and return it.
  //   int data;
    
  //   return data;
  // }

  private static class ExpressionNode {
    
    char operator;
    int operand;
    ExpressionNode left;
    ExpressionNode right;

    void postFix (ExpressionNode t) {
    // Base Case
    if (t == null)
      return;

    // Recurse Left
    postFix(t.left);
    // Recurse Right
    postFix(t.right);

    // Print out
    if (t.left != null)
      System.out.println(t.operator);
    else
      System.out.println(t.operand);

  }

  }
}