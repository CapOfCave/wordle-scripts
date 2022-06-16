package me.kecker.wordlegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileLoader {

    public static List<String> loadFile(String name) {
        try {
            InputStream inputStream = FileLoader.class.getResourceAsStream(name);
            assert inputStream != null;
            try (InputStreamReader streamReader =
                         new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                return reader.lines().toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
