## Lecture 2 
---



### Closure Theorems ###

The regular operations are closed under regular languages.

- Union
- Concatination
- Kleene Star

### Non-Deterministic Finite State Automata

It's the same kind of 5-Tuple as a DFA, except that $\delta$ now works in the following way:

- $\delta : Q \times \sum_\epsilon \rightarrow \mathcal{P}(Q)$ - Where $\mathcal{P}$ is the Powerset of all sets.

*Theorem:* If a language is recognized by an NDFA, then it is recognized by a DFA. Moreover, the proof is constructive. That is, there is an algorithm that derives the DFA from the NDFA.

*Note:* $\sum_\epsilon$ means that transitions are allows to use $\epsilon$, where $\epsilon$ represents the NULL string. In turn, this allows us to place $\epsilon$ transitions between NFAs to "jump" from accepting states to non-accepting states on another NFA, bridging the gap in between.

NFA's can be used to prove theorems about closures, such as Union, Concatenation, or Kleene Star ([Regular Operations](#closure-theorems)) of two or more DFAs. *See SIPSER 59-63 for more formal definitions/proofs*

NFA's can be seen as **persistent** machines; they exhaust all possiblities until a solution is reached.

#### Converting NFA to DFA
$NFA = (Q,\sum,\delta,q_0,F)$
$DFA = (Q',\sum',\delta',q_0',F')$

$Q = \mathcal{P}(Q)$



