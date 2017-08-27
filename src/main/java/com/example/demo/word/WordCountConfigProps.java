package com.example.demo.word;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("wordCount")
public class WordCountConfigProps {
    private boolean caseSensitive;
    private Words words;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public static class Words {
        private List<String> strings;

        public List<String> getSkip() {
            return this.strings;
        }

        public void setSkip(List<String> strings) {
            this.strings = strings;
        }
    }
}
