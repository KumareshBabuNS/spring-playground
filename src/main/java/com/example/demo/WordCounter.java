package com.example.demo;

import java.util.*;

class WordCounter {

    Map<String, Integer> count(String input) {
        String[] words = input.split("\\s+");
        List<String> wordsList = Arrays.asList(words);
        Map<String, Integer> output = new LinkedHashMap<String, Integer>();

        for (String word : wordsList) {
            word.replaceAll("\\p{P}", "");
            output.put(word, Collections.frequency(wordsList, word));
        }

        return output;
    }

}
