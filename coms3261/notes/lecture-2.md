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

