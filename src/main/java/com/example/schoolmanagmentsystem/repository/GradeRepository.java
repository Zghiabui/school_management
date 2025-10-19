package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}