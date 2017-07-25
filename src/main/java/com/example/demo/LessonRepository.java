package com.example.demo;

import org.springframework.data.repository.CrudRepository;

interface LessonRepository extends CrudRepository<Lesson, Long> {
}
