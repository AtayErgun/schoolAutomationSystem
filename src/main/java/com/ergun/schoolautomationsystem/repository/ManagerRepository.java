package com.ergun.schoolautomationsystem.repository;

import com.ergun.schoolautomationsystem.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
