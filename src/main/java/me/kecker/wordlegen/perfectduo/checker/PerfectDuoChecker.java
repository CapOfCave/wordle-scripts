package me.kecker.wordlegen.perfectduo.checker;

import me.kecker.wordlegen.Visualizer;
import me.kecker.wordlegen.perfectduo.PerfectDuoGenerator;
import me.kecker.wordlegen.perfectduo.WordleResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static me.kecker.wordlegen.perfectduo.PerfectDuoGenerator.loadAnswerList;

public class PerfectDuoChecker {

    public static void main(String[] args) {
        List<String> answerList = loadAnswerList();

        System.out.println("Print words pls");
        List<String> input = getList();

        List<WordleResult[]> wordleResults = input.stream()
                .map(word -> PerfectDuoGenerator.createSolutions(answerList, word))
                .map(solutions -> PerfectDuoGenerator.createAnswers(answerList, solutions))
                .toList();

        while (true) {
            System.out.println("Print evals pls");
            List<Integer> evals = getList().stream().map(Visualizer::readableToCompact).toList();

            // filter
            List<String> possibilities = answerList;
            for (int i = 0; i < wordleResults.size(); i++) {

                WordleResult[] relevantResults = wordleResults.get(i);
                WordleResult resultForGuess = relevantResults[evals.get(i)];
                possibilities = possibilities.stream().filter(resultForGuess.guesses()::contains).toList();
            }
            System.out.println(possibilities);

            System.out.println("next eval pls");
        }


    }

    private static List<String> getList() {
        List<String> input = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String lastInput = sc.nextLine();
        while (!lastInput.isBlank()) {
            if (lastInput.length() != 5) {
                throw new RuntimeException("Must be length 5, but was " + lastInput);
            }
            input.add(lastInput);
            lastInput = sc.nextLine();
        }
        return input;
    }


}
