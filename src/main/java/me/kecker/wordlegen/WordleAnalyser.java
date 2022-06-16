package me.kecker.wordlegen;

import java.util.List;

public class WordleAnalyser {

    public static int[][] createSolutions(List<String> answerList, List<String> guessList) {
        int[][] solutions = new int[answerList.size()][guessList.size()];

        // calculate and save wordle solutions
        for (int i = 0; i < answerList.size(); i++) {
//            if (i % 10 == 0) System.out.printf("%d%%%n", i * 100 / answerList.size());
            for (int j = 0; j < guessList.size(); j++) {
                int solution = WordleEvaluator.evaluateCompact(guessList.get(j), answerList.get(i));
                solutions[i][j] = solution;
            }
        }
        return solutions;
    }

}
