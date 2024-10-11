package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
}
