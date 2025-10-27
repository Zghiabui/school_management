package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    boolean existsByFacultyName(String facultyName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    boolean existsByEmailAndFacultyIdNot(String email, Long facultyId);
    boolean existsByPhoneAndFacultyIdNot(String phone, Long facultyId);
}
