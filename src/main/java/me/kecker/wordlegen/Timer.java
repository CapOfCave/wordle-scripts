package me.kecker.wordlegen;

public class Timer {

    private final long startTime;

    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    public static Timer start() {
        return new Timer();
    }

    public long diff() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
