package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WordCounterConfig {

    @Bean
    WordCounter getWordCounter(WordCountConfigProps props) {
        return new WordCounter(props);
    }

}

