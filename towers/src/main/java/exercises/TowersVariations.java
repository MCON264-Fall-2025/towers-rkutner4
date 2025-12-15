package exercises;

import java.util.Scanner;

/**
 * Exercise 23 — Towers of Hanoi Variation
 */
public class TowersVariations {

    // static counter for number of moves
    private static int count = 0;

    /**
     * Recursive method to solve the Towers of Hanoi variation.
     *
     * @param n    number of disks
     * @param from starting peg label (1, 2, or 3)
     * @param mid  middle peg (must be used for every move)
     * @param to   destination peg label
     */
    public static void solveVariation(int n, int from, int mid, int to) {
        if (n == 0) return; // Base case: no disks

        // Move n-1 disks from 'from' to 'mid' using 'to' as helper
        solveVariation(n - 1, from, to, mid);

        // Move largest disk from 'from' to 'to' via mid (counts as 2 moves)
        count += 2;

        // Move n-1 disks from 'mid' to 'to' using 'from' as helper
        solveVariation(n - 1, mid, from, to);
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

            count = 0; // reset count
            solveVariation(n, 1, 2, 3);
            System.out.printf("Number of moves (variation) = %d%n", count);
        }
        in.close();
    }
}

