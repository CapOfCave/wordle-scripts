package me.kecker.wordlegen.perfectduo;

import java.util.Collection;
import java.util.List;

import static me.kecker.wordlegen.Visualizer.compactToReadable;

public record WordleResult(int value, Collection<String> guesses) {

    public void add(String guess) {
        this.guesses.add(guess);
    }

    public int size() {
        return guesses.size();
    }

    @Override
    public String toString() {
        return "WordleResult{" +
                "result=" + compactToReadable(value) +
                ", guesses=" + guesses +
                '}';
    }
}
