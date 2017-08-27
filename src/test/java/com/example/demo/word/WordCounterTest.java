package com.example.demo.word;

import com.example.demo.word.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterTest {

    @Autowired
    private WordCounter counter;

    @Test
    public void testCount() throws Exception {
        Map result = this.counter.count("the quick brown fox, rupert, jumped over the brown dog!");
        Map<String, Integer> testInput = new LinkedHashMap<String, Integer>(){
            {
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