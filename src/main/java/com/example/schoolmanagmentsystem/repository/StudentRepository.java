package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClazz_ClassId(Long classId);
}
