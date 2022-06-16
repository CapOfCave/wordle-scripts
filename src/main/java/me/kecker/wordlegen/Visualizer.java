package me.kecker.wordlegen;

public class Visualizer {


    public static int readableToCompact(String readable) {
        return Integer.parseInt(readable, 3);
    }

    public static String compactToReadable(int pattern) {
        return String.format("%5s", Integer.toString(pattern, 3)).replace(' ', '0');
    }

    public static String twoCompactsToReadable(int pattern) {
        String str = Integer.toString(pattern, 3);
        return "0".repeat(10 - str.length()) + str;
    }
}
