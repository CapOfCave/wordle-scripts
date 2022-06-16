package me.kecker.wordlegen.perfectduo;

import me.kecker.wordlegen.Timer;
import me.kecker.wordlegen.WordleEvaluator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static me.kecker.wordlegen.FileLoader.loadFile;
import static me.kecker.wordlegen.Visualizer.compactToReadable;
import static me.kecker.wordlegen.WordleEvaluator.WORD_LENGTH;

public class PerfectDuoGenerator {

    public static void main(String[] args) {
        Timer timer = Timer.start();
        List<String> answerList = loadAnswerList();
        String firstGuess = "filet";

        int[] solutions = createSolutions(answerList, firstGuess);

        WordleResult[] answersForEvaluation = createAnswers(answerList, solutions);
        System.out.printf("Creating answers done in %d seconds", timer.diff());


        // sort results
        Arrays.sort(answersForEvaluation, Comparator.nullsLast(Comparator.comparing(WordleResult::size)));

        // print results
        for (WordleResult result : answersForEvaluation) {
            if (result == null) continue;
            System.out.printf("%s  -> [%d] %s%n", compactToReadable(result.value()), result.size(), result.guesses());
        }

        System.out.printf("All done in %d seconds", timer.diff());

    }

    public static WordleResult[] createAnswers(List<String> answerList, int[] solutions) {
        WordleResult[] answersForEvaluation = new WordleResult[(int) Math.pow(3, WORD_LENGTH)];

        for (int i = 0; i < answerList.size(); i++) {
//            if (i % 10 == 0) System.out.printf("%f%%%n", i * 100. / answerList.size());
            String answer = answerList.get(i);
            int solution = solutions[i];

            if (answersForEvaluation[solution] == null) {
                answersForEvaluation[solution] = new WordleResult(solution, new HashSet<>());
            }
            answersForEvaluation[solution].add(answer);
        }
        return answersForEvaluation;
    }

    public static int[] createSolutions(List<String> answerList, String guess) {
        int[] solutions = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            solutions[i] = WordleEvaluator.evaluateCompact(guess, answerList.get(i));
        }
        return solutions;
    }

    public static List<String> loadAnswerList() {
        return loadFile("/answer-list-duot.txt");
    }
}
