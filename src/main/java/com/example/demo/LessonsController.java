package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository lessonRepository;

    public LessonsController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    // CREATE
    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    // READ
    @GetMapping("/{id}")
    public Lesson one(@PathVariable Long id) {
        return this.lessonRepository.findOne(id);
    }

    // UPDATE, if the request body doesn't have an id that matches an already existing id
    // in the DB, a new entity will be added with the next available sequential id.
    // Your request body better be accurate.
    @PatchMapping("/{id}")
    public Lesson update(@RequestBody Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.lessonRepository.delete(id);
    }

    // LIST
    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.lessonRepository.findAll();
    }

}
