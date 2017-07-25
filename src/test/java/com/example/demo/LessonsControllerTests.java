package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"create test\", \"deliveredOn\": \"2017-07-25\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is("create test")))
                .andExpect(jsonPath("$.deliveredOn", is("2017-07-25")));
    }

    @Test
    @Transactional
    @Rollback
    public void testRead() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Lesson lesson = new Lesson();
        lesson.setTitle("read test");
        lesson.setDeliveredOn(new Date(sdf.parse("2017-07-25").getTime()));
        lessonRepository.save(lesson);

        int id = 1;

        MockHttpServletRequestBuilder request = get(String.format("/lessons/%d", id))
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.title", is("read test")))
                .andExpect(jsonPath("$.deliveredOn", is("2017-07-25")));

    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Lesson lesson1 = new Lesson();
        lesson1.setTitle("lesson 1 title");
        lesson1.setDeliveredOn(new Date(sdf.parse("2017-07-25").getTime()));

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("lesson 2 title");
        lesson2.setDeliveredOn(new Date(sdf.parse("2017-07-29").getTime()));

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("lesson 3 title");
        lesson3.setDeliveredOn(new Date(sdf.parse("2017-07-31").getTime()));

        List<Lesson> lessons = Arrays.asList(lesson1, lesson2, lesson3);

        lessonRepository.save(lessons);

        int id = 5;

        MockHttpServletRequestBuilder request = patch(String.format("/lessons/%d", id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"updated title\", \"deliveredOn\": \"2017-08-28\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.title", is("updated title")))
                .andExpect(jsonPath("$.deliveredOn", is("2017-08-28")));

    }

}
