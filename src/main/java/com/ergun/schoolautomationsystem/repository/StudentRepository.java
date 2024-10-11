package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
