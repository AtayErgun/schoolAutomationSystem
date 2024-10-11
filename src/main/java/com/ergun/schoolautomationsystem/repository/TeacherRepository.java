package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
