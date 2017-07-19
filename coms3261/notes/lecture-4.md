## Lecture 4
---
### Context Free Grammars

4-Tuple($V, \sum, R, S$)

- $V$: Variables / Non-Terminals
- $\sum$: Terminals
    + Can't have a non-terminal AND terminal $\rightarrow$ ($\sum \cap V \neq 0$)
- $R$: Finite set of rules of the following form:
    + $\alpha \rightarrow \beta$ where $\alpha \in V$
    + $\alpha$: $V$
    + $\beta$: $(V \cup \sum)^\ast$
- $S$: Start Symbol
    + Must be a Variable $\rightarrow S \in V$

Grammars generate terminal strings. In other words, you need to be able to replace all the variables with terminals.

**Example 1**

An equal amount of $a's$ and $b's$:

$\\{a^nb^n : n > 0\\}$

**Grammar**:

$G = S \rightarrow aSb \; \vert \; ab$ 

**Solution**: Suppose $n = 4$

$$
\begin{align*}
S &\rightarrow aSb\\
  &\rightarrow aaSbb\\
  &\rightarrow aaaSbbb\\
  &\rightarrow aaaabbbb\\
\end{align*}
$$

**Example 2**

Write a grammar that generates a legally nested set of parenthesis.

**Grammar**:

$G = S \rightarrow (S) \vert SS \vert (\;)$

**Solution**: Suppose we want to generate $(\;(\;)\;(\;)\;)(\;)$

$$
\begin{align*}
S &\rightarrow SS\\
  &\rightarrow (S)(S)\\
  &\rightarrow (SS)(\;)\\
  &\rightarrow (\;(\;)(\;)\;)(\;)\\
\end{align*}
$$











