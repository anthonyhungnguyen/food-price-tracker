package kwsearch;

import java.io.*;
import java.util.*;

public class WebPage {

    String url;
    Map<String, Integer> keywordFrequencies;

    WebPage(String url) {
        this.url = url;
        this.keywordFrequencies = new HashMap<>();
    }

    void parseKeywords(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            List<String> initialSplitWords = Arrays.asList(line.split(" "));
            // Further split each word by hyphens and clean up special characters
            List<String> cleanedWords = initialSplitWords.stream()
                    .flatMap(word -> Arrays.stream(word.split("-")))
                    .map(word -> word.replaceAll("[^a-zA-Z0-9'-]", ""))
                    .filter(word -> !word.isEmpty())
                    .toList();
            for (String key: cleanedWords) {
                addKeyword(key);
            }
        }
        reader.close();
    }

    void addKeyword(String keyword) {
        keywordFrequencies.put(keyword, keywordFrequencies.getOrDefault(keyword, 0) + 1);
    }

    int calculateRank(List<String> searchKeywords) {
        int rank = 0;
        for (String keyword : searchKeywords) {
            rank += keywordFrequencies.getOrDefault(keyword, 0);
        }
        return rank;
    }

}
