// In Order
public class ExprNode {
  char operator;
  int operand;
  ExprNode left;
  ExprNode right;

  void postFix (ExprNode t) {
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

// Prefix
public class ExprNode {
  char operator;
  int operand;
  ExprNode left;
  ExprNode right;

  void postFix (ExprNode t) {
    // Base Case
    if (t == null)
      return;
    
    // Print out
    if (t.left != null)
      System.out.println(t.operator);
    else
      System.out.println(t.operand);

    // Recurse Left
    postFix(t.left);
    // Recurse Right
    postFix(t.right);
    
  }
}

// Infix
public class ExprNode {
  char operator;
  int operand;
  ExprNode left;
  ExprNode right;

  
}

// Evaluation of a tree is gathering the numbers and applying the operands to them.

// Take in a postfix expression create the tree
// Use the algorithm for evaluating PostFix using a stack to create the tree
// The stack stores the Expression Node

/*
Stack
|5|
| |4
|-|
| |3
*/


// PostFix expression: 3 4 * 5 + (Should be separated by white space)
// OPERAND: Create the expression Node around the operand and push it to the stack
// OPERATOR: Create its expression Node around the operator and POP the stack
// First element should be the right subtree
// Switch case for the operator for the Evaluate method

/* STACK
5 -> POP
* -> PUSH 3 and 4 hang off of it
4 -> POP
3 -> POP
*/

// ExpressionTree class has a nested class called ExpressionNode
// Constructor takes a string (post fix expression)
// The stack algorithm is used inside the constructor



