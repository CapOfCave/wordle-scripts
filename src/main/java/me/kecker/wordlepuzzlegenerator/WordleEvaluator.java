package me.kecker.wordlepuzzlegenerator;

import java.util.Locale;

public class WordleEvaluator {

    public static final int WORD_LENGTH = 5;
    public static final int MAX_RESULT_BIT_LENGTH = WORD_LENGTH * 2;
    public static final int MAX_RESULT_VALUE = (int) Math.pow(2, MAX_RESULT_BIT_LENGTH) - 1;
    public static final int MAX_RESULT_VALUE_COMPACT = (int) Math.pow(3, WORD_LENGTH) - 1;



    /**
     * Return an integer that represents a 10 digit binary number.
     * <p>
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
                green += at(i);
                remainingOccurencesOfMove[guess.charAt(i) - 'a']--;
            }
        }

        int yellow = 0;
        for (int i = 0; i < WORD_LENGTH; i++) {
            if ((green & at(i)) != 0) continue;

            char guessed = guess.charAt(i);
            if (remainingOccurencesOfMove[guessed - 'a'] > 0) {
                yellow += at(i);
                remainingOccurencesOfMove[guessed - 'a']--;
            }
        }

        return (green << WORD_LENGTH) + yellow;
    }


    /**
     * Create an evaluation and store it in a base 3 integer
     */
    public static int evaluateCompact(String guess, String solution) {
        int evaluation = evaluate(guess, solution);
        int evaluationCompact = 0;
        int green = evaluation >> WORD_LENGTH;
        int yellow = evaluation & 0b11111;

        for (int i = 0; i < WORD_LENGTH; i++) {
            evaluationCompact *= 3;
            int tmp = (green & at(i)) >> (WORD_LENGTH - i - 1);
            // can only have values 1 or 0
            int isGreen = (green & at(i)) >> (WORD_LENGTH - i - 1);
            int isYellow = (yellow & at(i)) >> (WORD_LENGTH - i - 1);
            evaluationCompact += 2 * isGreen + isYellow;
        }
        return evaluationCompact;
    }

    private static int at(int i) {
        return 1 << (WORD_LENGTH - i - 1);
    }


}
