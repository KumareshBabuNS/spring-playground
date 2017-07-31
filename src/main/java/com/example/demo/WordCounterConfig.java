package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WordCounterConfig {

    @Bean
    WordCounter getWordCounter() {
        return new WordCounter();
    }

}

