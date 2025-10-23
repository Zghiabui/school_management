package com.example.schoolmanagmentsystem.repository;

import com.example.schoolmanagmentsystem.entity.Class;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("""
       SELECT COUNT(c) > 0
       FROM Class c
       WHERE c.room = :room
         AND c.dayOfWeek = :dow
         AND c.academicYear = :year
         AND c.semester = :semester
         AND (:start < c.endTime AND :end > c.startTime)  
         AND (:excludeId IS NULL OR c.classId <> :excludeId)
    """)
    boolean existsOverlap(@Param("room") String room,
                          @Param("dow") int dayOfWeek,
                          @Param("year") String academicYear,
                          @Param("semester") String semester,
                          @Param("start") LocalTime start,
                          @Param("end") LocalTime end,
                          @Param("excludeId") Long excludeId);
}
