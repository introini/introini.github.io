## Chapter 0  - Discrete Math Review
### Automata, Computability, and Complexity
---
*What are the fundamental capabilities and limitations of computers?*

#### Complexity Theory
*What makes some problems computationally hard and others easy?*
- No real answer to this.

- Understand what the root of the problem.
- Might need to settle for less than a perfect solution.
- Some problems are only hard in the worst case scenario, they're easy otherwise.
- Objective: Classify problems as *easy* or *hard*.

#### Computability Theory
- Some problems can't be solved by computers
    + Determine whether a mathematical statement is **True** or **False**.
- Classification of problems is *solvable* or *not solvable*.

#### Automata Theory
- Definitions and properties of models of computation.
    + **finite automaton**: Text processing, compilers, and hardware design.
    + **context-free grammar**: Programming languages and artificial intelligence.

### Mathematical Notations and Terms

#### Sets

- Group of objects represented as a unit.
    + **Elements / Members**: objects in a set.
- **Notation**: $S = \\{7,21,57\\}$
- Recal Notation from Discrete Math.
- Example:
    + $A = \\{1,2,3\\}$
    + $B = \\{4,\\{1,2,3\\},7\\}$

| - |True|False|
|---|:----:|:-----:|
|$A \in B$|$\checkmark$| - |
|$4 \in B$|$\checkmark$| - |
|$B \in B$| - |$\checkmark$|
|$A \subseteq B$| - |$\checkmark$|

**Why a set A cannot be an element of itself**

$R = \\{A : A \text{ is a set and } A \notin A \\}$

*Is R an element of R ($R \in R$)?*

R is a set of other sets, where the rule is "If the set is not a member of itself, then it belongs to R". If we answer "yes" to the above question it creates the following problem. If R is in R, then it means that it meets the rule required to be in R. But, the rule to be in R is precisely NOT to be in are, therefore creating a contradition and probing why a set cannot be a member of itself.

##### Cartesian Product
$(A \times B)$
The collection of ordered pairs where the first element comes from A, and the second element comes from B.

- Example:
    + $A = \\{1,2\\}$ , $B = \\{3,4\\}$
    + $(A \times B) = \\{(1,3),(1,4),(2,3),(2,4)\\}$

**Sequences and Tuples**

- List of objects in order.
- **Notation**: Usually written in parenthesis $(7,21,57)$
- Finite sequences are called **tuples**.
    + **k-tuples**: 3-tuple = $(1,2,3)$, 4-tuple = $(1,2,3,4)$
    + **2-tuple**: called an ordered pair.

### Functions and Relations 

$f : A \rightarrow B$

- Recall from Discrete Mathematics



