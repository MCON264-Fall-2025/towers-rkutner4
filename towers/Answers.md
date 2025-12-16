#  MCON 264 — Recursion Assignment: Towers of Hanoi × 3


##  Overview

In this lab, you implemented three recursive versions of the Towers of Hanoi problem and (optionally) one iterative version.  
Each part reinforces a key concept of recursion — base case, recursive case, and growth pattern — and illustrates how recursion can be refactored or replaced by iteration.

---

## Part 1 — Classic Towers of Hanoi (`TowersOfHanoi.java`)

### 1. Base Case
_Describe the base condition that stops recursion (for example, what happens when `n == 0`?)._

> ✎ The recursion stops when `n == 0`. At this point, there are no disks to move, so the function returns immediately.

### 2. Recursive Case
_Explain the sequence of recursive calls and what each represents._

> ✎ For `n >= 1`:
1. Move the top `n-1` disks from `from` peg to `aux` peg (recursive call).
2. Move the largest disk (`n`) from `from` to `to`.
3. Move the `n-1` disks from `aux` peg to `to` peg (recursive call).

### 3. Sample Trace (for n = 3)

| Move # | From | To |
|:--:|:--:|:--:|
| 1 | A | C |
| 2 | A | B |
| 3 | C | B |
| … |  |  |

_Total moves = 2ⁿ − 1 = 7 (for n = 3)_

---

## Part 2 — Counting Moves (`TowersExercise21.java`)

### 1. Approach
_How did you modify the standard recursion to count rather than print moves?_

>  Instead of printing moves, we increment a static counter `count` each time a disk is “moved.”  
Recursive calls remain the same.

### 2. Verification of Formula
_Complete the table and verify that count = 2ⁿ − 1._

| n | Expected (2ⁿ − 1) | Program Output | Matches? (Y/N) |
|:--:|:--:|:--:|:--:|
| 1 | 1 | 1 | Y |
| 2 | 3 | 3 | Y |
| 3 | 7 | 7 | Y |
| 4 | 15 | 15 | Y |
| 5 | 31 | 31 | Y |

### 3. Reflection
_What changes when you replace printed moves with a counter? What are the pros and cons?_

> Replacing printed moves with a counter reduces memory and console output.  
Pros: cleaner, easier to analyze growth; Cons: cannot verify exact move sequence without additional data structure.

---

## Part 3 — Hanoi Variation (`TowersVariations.java`)

### 1. New Rule
_Every move must pass through the middle peg. How does this alter the recursion?_

> Each move of the largest disk must occur in two steps: `from → mid` and `mid → to`.  
To move `n` disks from the starting peg to the destination peg, the algorithm:
1. Moves `n-1` disks from `from` to `to`,
2. Moves disk `n` from `from` to `mid`,
3. Moves `n-1` disks from `to` back to `from`,
4. Moves disk `n` from `mid` to `to`,
5. Moves `n-1` disks from `from` to `to`.
   This results in three recursive calls per level and produces the recurrence `T(n) = 3T(n−1) + 2`.

### 2. Observed Move Counts

| n | Expected ≈ 3ⁿ − 1 | Program Output | Matches? (Y/N) |
|:--:|:--:|:--:|:--:|
| 1 | 2 | 2 | Y |
| 2 | 8 | 8 | Y |
| 3 | 26 | 26 | Y |
| 4 | 80 | 80 | Y |

### 3. Analysis
_Why does this variation grow faster than the standard version? How do additional move constraints affect complexity?_

> ✎ This variation grows faster because each disk move requires two separate steps through the middle peg, and the recursive structure now contains three recursive subproblems instead of two. This changes the recurrence from `2ⁿ − 1` to `3ⁿ − 1`, increasing the total number of operations exponentially.

---

## Comparative Analysis

### 1. Growth Patterns

| Version | Approx. Formula | Growth Type |
|:--|:--|:--|
| Standard | 2ⁿ − 1 | Exponential |
| Variation | 3ⁿ − 1 | Exponential (faster) |
| Iterative (Optional) | 2ⁿ − 1 | Same as recursive but loop based |

### 2. Stack Depth and Memory
_Estimate the maximum recursion depth before StackOverflowError and discuss how stack size (–Xss flag) affects this._

> ✎Maximum recursion depth is `n` for standard Towers and for the variation. Stack size limits recursion (`StackOverflowError`) for very large `n` (~20–30 depending on JVM). Increasing `-Xss` flag allows deeper recursion.

---

## Part 4 — Beyond Recursion (Extra Credit)

### Iterative / Explicit-Stack Version (`TowersIterative.java`)

1. How does your iterative version simulate recursion?
2. How did you track pending calls or frames?
3. Which version (r vs iterative) is clearer? Why?

> ✎ The iterative version uses an explicit stack (e.g., Stack<Frame> or Deque<Frame>) to simulate the call stack that recursion naturally uses. Each stack frame contains the current state of a “recursive call” — the number of disks to move, the source, auxiliary, and destination pegs. The algorithm repeatedly pops frames, processes moves, and pushes new frames onto the stack to represent pending recursive calls. Each frame stores (n, from, aux, to) and a flag or step counter to indicate which stage of the recursion it is in. When a frame is popped, the algorithm checks if n > 0 and, depending on the step, either counts/moves the largest disk or pushes the next sub-problem frames onto the stack. This ensures that all moves are executed in the correct order. The recursive version is usually clearer because it expresses the solution naturally in terms of the problem definition — “move n-1 disks, move largest, move n-1 disks” — without explicitly managing a stack or bookkeeping. The iterative version, while more memory-efficient in some cases, is more verbose and harder to read due to manual stack management and state tracking.

---

## Discussion &amp; Reflection

1. What three questions can you use to verify a recursive solution works?
2. How do the base case and recursive case cooperate to guarantee termination?
3. What is the trade-off between elegance and efficiency in recursion?

> ✎ Does the function handle the base case correctly (e.g., n == 0) and terminate? Does each recursive call reduce the problem size so that it eventually reaches the base case? Do the results of recursive calls combine correctly to produce the expected output (e.g., correct move sequence or count)?
>  The base case provides a stopping condition that prevents infinite recursion. The recursive case breaks the problem into smaller sub-problems that eventually reach the base case. Together, they ensure that every chain of calls eventually terminates.
>  Recursion is elegant and closely mirrors the problem definition, making code shorter and easier to understand. However, it can be less efficient due to function call overhead and risk of stack overflow for large inputs. Iterative solutions can be more memory-efficient and faster, but usually at the cost of clarity and conciseness.

---

## References (APA or MLA format)

- Dale, N., Joyce, D., &amp; Weems, C. (2016). *Object-Oriented Data Structures Using Java* (Ch. 3 Recursion). Jones &amp; Bartlett.
- YouTube CS50 Recursion, GeeksForGeeks Hanoi, tower of Hanoi stimulation, chat gpt
---

**Submission Checklist**

- [ ] `TowersOfHanoi.java` — implemented and tested
- [ ] `TowersExercise21.java` — counts moves correctly
- [ ] `TowersVariations.java` — middle-peg constraint implemented
- [ ] (Extra Credit) `TowersIterative.java` — loop or explicit stack solution
- [ ] All JUnit tests pass (green on GitHub Actions)
- [ ] This `ANSWERS.md` file completed and committed to repo  
