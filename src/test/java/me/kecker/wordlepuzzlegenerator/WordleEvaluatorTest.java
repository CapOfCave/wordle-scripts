package me.kecker.wordlepuzzlegenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleEvaluatorTest {

    @Test
    void testOneGreenLetter() {
        int evaluation = WordleEvaluator.evaluate("thill", "trace");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("1000000000", evaluationBinary);
    }

    @Test
    void testOneYellowLetter() {
        int evaluation = WordleEvaluator.evaluate("third", "brace");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0000000010", evaluationBinary);
    }

    @Test
    void testMultipleLettersCorrect() {
        int evaluation = WordleEvaluator.evaluate("theft", "tests");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("1000000101", evaluationBinary);
    }

    @Test
    void testNoSecondYellow() {
        int evaluation = WordleEvaluator.evaluate("creek", "feuds");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0000000100", evaluationBinary);
    }

    @Test
    void testNoYellowIfGreen() {
        int evaluation = WordleEvaluator.evaluate("seeks", "brent");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0010000000", evaluationBinary);
    }
}