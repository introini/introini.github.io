## Lecture 1
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

### Some Definitions for Computability

Alphabet
: Non-Empty finite set of symbols

String
: Finite sequence of symbols from the *alphabet*

    - Length
    - Empty string (denoted as $\epsilon$)
    - Blank / Space is of length 1

Language
: Set of *strings*

    - Does not need to be finite

### Deterministic Finite State Automata

**Example 1**

<svg width="500" height="200" version="1.1" xmlns="http://www.w3.org/2000/svg">
    <ellipse stroke="black" stroke-width="1" fill="none" cx="86.5" cy="102.5" rx="30" ry="30"/>
    <text x="77.5" y="108.5" font-family="Times New Roman" font-size="20">q&#8321;</text>
    <ellipse stroke="black" stroke-width="1" fill="none" cx="350.5" cy="102.5" rx="30" ry="30"/>
    <text x="341.5" y="108.5" font-family="Times New Roman" font-size="20">q&#8323;</text>
    <ellipse stroke="black" stroke-width="1" fill="none" cx="215.5" cy="102.5" rx="30" ry="30"/>
    <text x="206.5" y="108.5" font-family="Times New Roman" font-size="20">q&#8322;</text>
    <ellipse stroke="black" stroke-width="1" fill="none" cx="215.5" cy="102.5" rx="24" ry="24"/>
    <path stroke="black" stroke-width="1" fill="none" d="M 202.275,75.703 A 22.5,22.5 0 1 1 228.725,75.703"/>
    <text x="210.5" y="26.5" font-family="Times New Roman" font-size="20">1</text>
    <polygon fill="black" stroke-width="1" points="228.725,75.703 237.473,72.17 229.382,66.292"/>
    <path stroke="black" stroke-width="1" fill="none" d="M 240.222,85.707 A 99.263,99.263 0 0 1 325.778,85.707"/>
    <polygon fill="black" stroke-width="1" points="325.778,85.707 320.714,77.748 316.404,86.772"/>
    <text x="277.5" y="67.5" font-family="Times New Roman" font-size="20">0</text>
    <path stroke="black" stroke-width="1" fill="none" d="M 324.563,117.392 A 110.532,110.532 0 0 1 241.437,117.392"/>
    <polygon fill="black" stroke-width="1" points="241.437,117.392 246.969,125.033 250.73,115.767"/>
    <text x="270.5" y="146.5" font-family="Times New Roman" font-size="20">0,1</text>
    <polygon stroke="black" stroke-width="1" points="116.5,102.5 185.5,102.5"/>
    <polygon fill="black" stroke-width="1" points="185.5,102.5 177.5,97.5 177.5,107.5"/>
    <text x="146.5" y="123.5" font-family="Times New Roman" font-size="20">1</text>
    <path stroke="black" stroke-width="1" fill="none" d="M 73.275,75.703 A 22.5,22.5 0 1 1 99.725,75.703"/>
    <text x="81.5" y="26.5" font-family="Times New Roman" font-size="20">0</text>
    <polygon fill="black" stroke-width="1" points="99.725,75.703 108.473,72.17 100.382,66.292"/>
    <polygon stroke="black" stroke-width="1" points="16.5,102.5 56.5,102.5"/>
    <polygon fill="black" stroke-width="1" points="56.5,102.5 48.5,97.5 48.5,107.5"/>
</svg>

**Described in plain English**

An even number of 0s after the last 1 will be accepted by this machine.

Legal Automata
: A 5-tuple $(Q,\sum,\delta,q_0,F)$

    - $Q    :$ Finite set of states
    - $\sum :$ Finite set (the alphabet)
    - $\delta :$ A function $Q \times \sum : \rightarrow Q$ (transition)
    - $q_0  :$ Start state (where the first arrow is pointing to)
    - $F    :$ Final, subset of $Q$,  set of accepting states

$\delta :$ Every (State,Alphabet) has to have a unique arrow corresponding to it: 

The 5-tuple that describes [Example 1](#state-diagram---automata):

- $Q : {q_0,q_1,q_2}$
- $\sum : {0,1}$
- $\delta :$

|  | 0 | 1 | 
|---|---|---|
|$q_1$|$q_1$|$q_2$|
|$q_2$|$q_2$|$q_3$|
|$q_3$|$q_3$|$q_2$|

- $q_0 : {q_1}$
- $F : {q_2}$ 

L(M)
: A language of M which is the set of all strings accepted by M

Regular Language <a name="regular-language"></a>
: A language is regular if there is some FSA that accepts it.
