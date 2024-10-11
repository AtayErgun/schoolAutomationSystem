package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {
}
