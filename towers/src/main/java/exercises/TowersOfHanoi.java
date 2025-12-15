package exercises;

import java.util.List;

/**
 * Towers of Hanoi (Recursive).
 *
 * PROBLEM:
 * - Move a stack of n disks from peg 'from' to peg 'to' using peg 'aux',
 *   moving one disk at a time and never placing a larger disk on a smaller disk.
 *
 * RECURSION DESIGN:
 * - Base case:
 *     * n == 0 -> nothing to do (return).
 * - Recursive idea for n >= 1:
 *     1) Move n-1 disks from 'from' to 'aux' (using 'to' as auxiliary).
 *     2) Move the largest disk (disk n) from 'from' to 'to'.
 *     3) Move n-1 disks from 'aux' to 'to' (using 'from' as auxiliary).
 *
 * COMPLEXITY:
 * - Moves: 2^n - 1
 * - Time: O(2^n)
 * - Space: O(n) recursion depth
 *
 * SUGGESTION:
 * - Collect moves in a List<String> like "A -> C" so tests can assert the exact sequence.
 */
public class TowersOfHanoi {

    /**
     * @param n     number of disks (assume n >= 0)
     * @param from  source peg label, e.g. 'A'
     * @param aux   auxiliary peg label, e.g. 'B'
     * @param to    target peg label, e.g. 'C'
     * @param moves output list to record each move as a string "X -> Y"
     */
    public static void solve(int n, char from, char aux, char to, List<String> moves) {
        if (n == 0) return; // Base case: no disks to move

        // 1) Move n-1 disks from 'from' to 'aux'
        solve(n - 1, from, to, aux, moves);

        // 2) Move the largest disk (disk n) from 'from' to 'to'
        moves.add(from + " -> " + to);

        // 3) Move n-1 disks from 'aux' to 'to'
        solve(n - 1, aux, from, to, moves);
    }
}



