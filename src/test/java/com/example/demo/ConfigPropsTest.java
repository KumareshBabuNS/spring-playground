package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
    "wordCount.caseSensitive=true",
    "wordCount.words.skip[0]=skip",
    "wordCount.words.skip[1]=these",
    "wordCount.words.skip[2]=words"
})

public class ConfigPropsTest {

    @Autowired
    private WordCountConfigProps configProps;

    @Test
    public void testConfigProperties() {
        assertThat(configProps.isCaseSensitive(), equalTo(true));
        assertThat(configProps.getWords().getSkip(), contains("skip", "these", "words"));
    }

}
