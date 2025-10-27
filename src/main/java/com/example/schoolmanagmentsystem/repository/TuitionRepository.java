package com.example.schoolmanagmentsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.schoolmanagmentsystem.entity.Tuition;

public interface TuitionRepository extends JpaRepository<Tuition, Long>, JpaSpecificationExecutor<Tuition> {
            java.util.Optional<com.example.schoolmanagmentsystem.entity.Tuition> findByStudentAndSemester(com.example.schoolmanagmentsystem.entity.Student student, String semester);
}