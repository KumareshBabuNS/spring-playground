package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query(value = "SELECT * FROM LESSONS WHERE delivered_on BETWEEN :date1 AND :date2", nativeQuery = true)
    Iterable<Lesson> findLessonsBetween(@Param("date1") Date date1, @Param("date2") Date date2);
}
