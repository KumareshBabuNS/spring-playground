package com.example.demo.word;

import com.example.demo.word.WordCountConfigProps;
import com.example.demo.word.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WordCounterConfig {

    @Bean
    WordCounter getWordCounter(WordCountConfigProps props) {
        return new WordCounter(props);
    }

}

