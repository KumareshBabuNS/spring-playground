package com.example.demo.lesson;

import com.example.demo.lesson.Lesson;
import com.example.demo.lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lessons")
class LessonsController {

    @Autowired
    LessonRepository lessonRepository;

    // CREATE
    @PostMapping("")
    Lesson create(@RequestBody Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    // READ
    @GetMapping("/{id}")
    Lesson one(@PathVariable Long id) {
        return lessonRepository.findOne(id);
    }

    // UPDATE
    @PatchMapping("/{id}")
    Lesson update(@PathVariable Long id, @RequestBody Lesson lesson) {
        Lesson lessonEntity = lessonRepository.findOne(id);
        lessonEntity.setTitle(lesson.getTitle());
        lessonEntity.setDeliveredOn(lesson.getDeliveredOn());

        return lessonRepository.save(lessonEntity);
    }

    // DELETE
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        lessonRepository.delete(id);
    }

    // LIST
    @GetMapping("")
    Iterable<Lesson> all() {
        return lessonRepository.findAll();
    }

    // FIND BY TITLE
    @GetMapping("/find/{title}")
    Lesson byTitle(@PathVariable String title) {
        return lessonRepository.findByTitle(title);
    }

    // FIND BETWEEN DATES
    @GetMapping("/between")
    Iterable<Lesson> betweenDates(@RequestParam String date1, @RequestParam String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return lessonRepository.findLessonsBetween(new Date(sdf.parse(date1).getTime()), new Date(sdf.parse(date2).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Lesson> lessons = Arrays.asList();

        return lessons;
    }

}
