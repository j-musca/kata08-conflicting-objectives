package org.example.extensible;

import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnglishDictionary implements Dictionary {

    private static final Path WORD_FILE_PATH = Paths.get("src/main/resources/english-words.txt");
    private final Set<String> englishWords;

    public EnglishDictionary() {
        try {
            englishWords = ImmutableSet.copyOf(Files.readAllLines(WORD_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Could not read dictionary! Reason: " + e.getMessage(), e);
        }
    }

    public boolean isWordExisting(final String word) {
        return englishWords.contains(word);
    }

    public List<String> getWordsWithLength(final int wordLength) {
        return englishWords.stream().parallel()
                .filter((String word) -> word.length() == wordLength)
                .collect(Collectors.toList());
    }
}
