package me.kecker.wordlepuzzlegenerator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static me.kecker.wordlepuzzlegenerator.WordleEvaluator.WORD_LENGTH;

public class PuzzleGenerator {

    public static void main(String[] args) throws IOException {
        List<String> answerList = WordLoader.loadAnswerList();
        List<String> guessList = WordLoader.loadAnswerList();
        System.out.println("Starting...");
        int[][] solutions = new int[answerList.size()][guessList.size()];

        // calculate and save wordle solutions
        for (int i = 0; i < answerList.size(); i++) {
//            if (i % 10 == 0) System.out.printf("%d%%%n", i * 100 / answerList.size());
            for (int j = 0; j < guessList.size(); j++) {
                int solution = WordleEvaluator.evaluateCompact(guessList.get(j), answerList.get(i));
                solutions[i][j] = solution;
            }
        }

        int[] taken = new int[(int) Math.pow(3, WORD_LENGTH * 2)];

        Path file = args.length > 0 ? Paths.get(args[0]) : Paths.get(System.getProperty("user.home"), "puzzles.txt");
        FileWriter fw = new FileWriter(file.toFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (int j = 0; j < guessList.size(); j++) {
            if (j % 10 == 0) System.out.printf("%f%%%n", j * 100. / guessList.size());
            for (int k = j + 1; k < guessList.size(); k++) {
//                System.out.printf("%s/%s%n", guessList.get(j), guessList.get(k));
                for (int i = 0; i < answerList.size(); i++) {
                    int pattern1 = solutions[i][j];
                    int pattern2 = solutions[i][k];
                    int combinedPattern = (pattern1 * (int) Math.pow(3, WORD_LENGTH)) + pattern2;
                    if (taken[combinedPattern] == 0) {
                        taken[combinedPattern] = i + 1;
                    } else {
                        taken[combinedPattern] = -1;
                    }
                }
                for (int pattern = 0; pattern < taken.length; pattern++) {
                    if (taken[pattern] <= 0) continue;
                    int answerIndex = taken[pattern] - 1;
                    bw.write(String.format("%s,%s,%s,%s,%d", guessList.get(j), guessList.get(k), twoCompactsToReadable(pattern), answerList.get(answerIndex), answerIndex));
                    bw.newLine();
                }

                bw.newLine();
                Arrays.fill(taken, 0);
            }


        }

        bw.close();
        System.out.println("Completed.");
    }

    private static String compactToReadable(int pattern) {
        return String.format("%5s", Integer.toString(pattern, 3)).replace(' ', '0');
    }

    private static String twoCompactsToReadable(int pattern) {
        return String.format("%10s", Integer.toString(pattern, 3)).replace(' ', '0');
    }


}
