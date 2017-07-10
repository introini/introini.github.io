## Chapter 1 
---
### Some Definitions for Computability

Alphabet
: Non-Empty finite set of symbols

String
: Finite sequence of symbols from the *alphabet*

    - Length
    - Empty string (denoted as $\in$)
    - Blank / Space is of length 1

Language
: Set of *strings*

    - Does not need to be finite


### State Diagram - Automata

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





