package com.example.demo;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WordCounterTest {

    @Test
    public void count() throws Exception {
        WordCounter counter = new WordCounter();
        Map result = counter.count("the quick brown fox, rupert, jumped over the brown dog!");
        Map<String, Integer> testInput = new LinkedHashMap<String, Integer>(){
            {
                put("the", 2);
                put("quick", 1);
                put("brown", 2);
                put("fox", 1);
                put("rupert", 1);
                put("jumped", 1);
                put("over", 1);
                put("dog", 1);
            }
        };

        assertEquals(testInput, result);
    }

}