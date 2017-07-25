package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
