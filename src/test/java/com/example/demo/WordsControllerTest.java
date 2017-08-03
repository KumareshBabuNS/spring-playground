package com.example.demo;

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
public class WordsControllerTest {

    @Autowired
    private WordsController controller;

    @Test
    public void testPostWordCount() throws Exception {
        String requestBody = "do you how do?";
        Map<String, Integer> testInput = new LinkedHashMap<String, Integer>(){
            {
                put("do", 2);
                put("you", 1);
                put("how", 1);
            }
        };

        assertEquals(testInput, this.controller.postWordCount(requestBody));
    }

}