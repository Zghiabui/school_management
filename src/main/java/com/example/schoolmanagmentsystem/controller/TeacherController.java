package com.example.schoolmanagmentsystem.controller;

import com.example.schoolmanagmentsystem.dto.TeacherDTO;
import com.example.schoolmanagmentsystem.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO savedTeacher = teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long teacherId) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(teacherId);
        if (teacherDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherDTO);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDTO>> searchTeachers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long facultyId) {
        List<TeacherDTO> teachers = teacherService.searchTeachers(name, email, facultyId);
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") Long teacherId, @Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(teacherId, teacherDTO);
        if (updatedTeacher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok("Teacher deleted successfully!");
    }
}