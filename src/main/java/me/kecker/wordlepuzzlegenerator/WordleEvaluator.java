package me.kecker.wordlepuzzlegenerator;

import java.util.Locale;

public class WordleEvaluator {

    private static final int WORD_LENGTH = 5;

    /**
     * Return an integer that represents a 10 digit binary number.
     *
     * The first 5 bits indicate if the letter was green, the next 5 bits indicate if the letter was yellow.
     */
    public static int evaluate(String guess, String solution) {
        assert guess.length() == WORD_LENGTH;
        assert solution.length() == WORD_LENGTH;
        assert guess.toLowerCase(Locale.ROOT).equals(guess);
        assert solution.toLowerCase(Locale.ROOT).equals(solution);

        int[] remainingOccurencesOfMove = new int[26];

        for (char c : solution.toCharArray()) {
            remainingOccurencesOfMove[c - 'a']++;
        }

        // conceptually a boolean array but better performance
        int green = 0;
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                green += (1 << (WORD_LENGTH - i - 1));
                remainingOccurencesOfMove[guess.charAt(i) - 'a']--;
            }
        }

        int yellow = 0;
        for (int i = 0; i < WORD_LENGTH; i++) {
            if ((green & (1 << (WORD_LENGTH - i - 1))) != 0) continue;

            char guessed = guess.charAt(i);
            if (remainingOccurencesOfMove[guessed - 'a'] > 0) {
                yellow += (1 << (WORD_LENGTH - i - 1));
                remainingOccurencesOfMove[guessed - 'a']--;
            }
        }

        return (green << WORD_LENGTH) + yellow;
    }

}
