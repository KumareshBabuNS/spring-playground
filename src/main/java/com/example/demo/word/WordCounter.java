package com.example.demo.word;

import com.example.demo.word.WordCountConfigProps;

import java.util.*;

class WordCounter {

    final private WordCountConfigProps props;

    public WordCounter(WordCountConfigProps props) {
        this.props = props;
    }

    Map<String, Integer> count(String input) {
        String[] words = input.replaceAll("\\p{P}", "").split("\\s+");
        List<String> wordsList = new ArrayList<>();
        Map<String, Integer> output = new LinkedHashMap<>();

        if (!props.isCaseSensitive()) {
            for (String word : words) {
                wordsList.add(word.toLowerCase());
            }
        } else {
            for (String word : words) {
                wordsList.add(word);
            }
        }

        for (String word : wordsList) {
            if (Collections.frequency(props.getWords().getSkip(), word) == 0) {
                output.put(word, Collections.frequency(wordsList, word));
            }
        }

        return output;
    }

}
