package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}

