package me.kecker.wordlepuzzlegenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleEvaluatorTest {

    @Test
    void testEvaluateOneGreenLetter() {
        int evaluation = WordleEvaluator.evaluate("thill", "trace");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("1000000000", evaluationBinary);
    }

    @Test
    void testEvaluateOneYellowLetter() {
        int evaluation = WordleEvaluator.evaluate("third", "brace");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0000000010", evaluationBinary);
    }

    @Test
    void testEvaluateMultipleLettersCorrect() {
        int evaluation = WordleEvaluator.evaluate("theft", "tests");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("1000000101", evaluationBinary);
    }

    @Test
    void testEvaluateNoSecondYellow() {
        int evaluation = WordleEvaluator.evaluate("creek", "feuds");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0000000100", evaluationBinary);
    }

    @Test
    void testEvaluateNoYellowIfGreen() {
        int evaluation = WordleEvaluator.evaluate("seeks", "brent");
        String evaluationBinary = String.format("%10s", Integer.toBinaryString(evaluation)).replace(' ', '0');
        assertEquals("0010000000", evaluationBinary);
    }

    @Test
    void testEvaluateCompactOneGreenLetter() {
        int evaluation = WordleEvaluator.evaluateCompact("thill", "trace");
        String evaluationString = String.format("%5s", Integer.toString(evaluation, 3)).replace(' ', '0');
        assertEquals("20000", evaluationString);
    }

    @Test
    void testEvaluateCompactOneYellowLetter() {
        int evaluation = WordleEvaluator.evaluateCompact("third", "brace");
        String evaluationString = String.format("%5s", Integer.toString(evaluation, 3)).replace(' ', '0');
        assertEquals("00010", evaluationString);
    }

    @Test
    void testEvaluateCompactMultipleLettersCorrect() {
        int evaluation = WordleEvaluator.evaluateCompact("theft", "tests");
        String evaluationString = String.format("%5s", Integer.toString(evaluation, 3)).replace(' ', '0');
        assertEquals("20101", evaluationString);
    }

    @Test
    void testEvaluateCompactNoSecondYellow() {
        int evaluation = WordleEvaluator.evaluateCompact("creek", "feuds");
        String evaluationString = String.format("%5s", Integer.toString(evaluation, 3)).replace(' ', '0');
        assertEquals("00100", evaluationString);
    }

    @Test
    void testEvaluateCompactNoYellowIfGreen() {
        int evaluation = WordleEvaluator.evaluateCompact("seeks", "brent");
        String evaluationString = String.format("%5s", Integer.toString(evaluation, 3)).replace(' ', '0');
        assertEquals("00200", evaluationString);
    }
}