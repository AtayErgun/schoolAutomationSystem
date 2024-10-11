package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
