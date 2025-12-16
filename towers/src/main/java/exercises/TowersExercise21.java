package exercises;

import java.util.Scanner;

/**
 * Exercise 21 — Towers of Hanoi with Move Counting
 */
public class TowersExercise21 {

    // static counter for number of moves
    private static int count = 0;

    /**
     * Recursive solver for the Towers of Hanoi.
     * @param n    number of disks
     * @param from source peg
     * @param aux  auxiliary peg
     * @param to   destination peg
     */
    public static void solve(int n, char from, char aux, char to) {
        if (n == 0) return; // Base case: no moves

        // 1) Move n-1 disks from 'from' to 'aux'
        solve(n - 1, from, to, aux);

        // 2) Move disk n from 'from' to 'to' → increment count
        count++;

        // 3) Move n-1 disks from 'aux' to 'to'
        solve(n - 1, aux, from, to);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter number of rings (negative to quit): ");
            int n = in.nextInt();
            if (n < 0) {
                System.out.println("Goodbye!");
                break;
            }

            count = 0;               // reset counter
            solve(n, 'A', 'B', 'C'); // recursive call
            System.out.printf("Number of moves = %d%n", count);
        }
        in.close();
    }
}

