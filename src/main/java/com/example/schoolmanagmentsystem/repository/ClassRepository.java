package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Class;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("""
  SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END
  FROM Class c
  WHERE c.room = :room
    AND c.studyDate = :studyDate
    AND c.academicYear = :academicYear
    AND c.semester = :semester
    AND (c.startTime < :endTime AND c.endTime > :startTime)
    AND (:excludeId IS NULL OR c.classId <> :excludeId)
""")
    boolean existsOverlapByDate(String room,
                                LocalDate studyDate,
                                String academicYear,
                                String semester,
                                LocalTime startTime,
                                LocalTime endTime,
                                Long excludeId);
}
