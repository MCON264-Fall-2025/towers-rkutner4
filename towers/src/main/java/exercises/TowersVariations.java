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
     * @param from starting peg label
     * @param mid  middle peg (must be used for every move)
     * @param to   destination peg label
     */
    public static void solveVariation(int n, int from, int mid, int to) {
        if (n == 0) {
            return; // base case
        }

        // 1) Move n-1 disks from 'from' to 'to'
        solveVariation(n - 1, from, mid, to);

        // 2) Move disk n from 'from' -> 'mid'
        count++;

        // 3) Move n-1 disks from 'to' back to 'from'
        solveVariation(n - 1, to, mid, from);

        // 4) Move disk n from 'mid' -> 'to'
        count++;

        // 5) Move n-1 disks from 'from' to 'to'
        solveVariation(n - 1, from, mid, to);
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

            count = 0;
            solveVariation(n, 1, 2, 3);
            System.out.printf("Number of moves (variation) = %d%n", count);
        }
        in.close();
    }
}

