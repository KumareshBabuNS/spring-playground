package com.example.demo.word;

import com.example.demo.word.WordCounter;
import com.example.demo.word.WordsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(WordsController.class)
@AutoConfigureMockMvc(secure = false)
public class WordsControllerWebMvcTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    WordCounter counter;

    @Before
    public void setup() {
        Map<String, Integer> testOutput = new LinkedHashMap<String, Integer>(){
            {
                put("do", 2);
                put("you", 1);
                put("how", 1);
            }
        };

        when(counter.count("do you how do?")).thenReturn(testOutput);
    }

    @Test
    public void testPostWordCount() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN)
                .content("do you how do?");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.do", is(2)))
                .andExpect(jsonPath("$.how", is(1)))
                .andExpect(jsonPath("$.you", is(1)));
    }

}
