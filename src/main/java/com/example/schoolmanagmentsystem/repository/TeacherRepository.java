package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    List<Teacher> findByFaculty(Faculty faculty);
}