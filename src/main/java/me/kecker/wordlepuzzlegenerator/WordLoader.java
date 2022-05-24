package me.kecker.wordlepuzzlegenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class WordLoader {

    public static List<String> loadGuessList() {
        try {
            return loadFile("/guess-list.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> loadAnswerList() {
        try {
            return loadFile("/answer-list.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> loadFile(String name) throws IOException {
        InputStream inputStream = WordLoader.class.getResourceAsStream(name);
        assert inputStream != null;
        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            return reader.lines().toList();
        }
    }
}
