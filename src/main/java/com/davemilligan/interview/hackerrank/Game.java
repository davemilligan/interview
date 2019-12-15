package com.davemilligan.interview.hackerrank;

public class Game {

    public static void main(String[] args) {
        int[] arr = new int[] {0, 0, 1, 1, 1, 0};
        System.out.println(canWin(3, arr));
    }

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        return canWin(game, leap, 0);
    }

    public static boolean canWin(int[] game, int leap , int i) {
        if (i >= game.length)
            return true;
        if (game[i]==0) {
            game[i] = 1;
            if (canWin(game, leap, i+leap) || (canWin(game, leap, i+1))) {
                return true;
            }

            if (i > 0) {
                if (canWin(game, leap, i-1)){
                    return true;
                }
            }
        }
        return false;
    }
}
