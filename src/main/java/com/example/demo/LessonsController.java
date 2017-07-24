package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository lessonRepository;

    public LessonsController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.lessonRepository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

}
