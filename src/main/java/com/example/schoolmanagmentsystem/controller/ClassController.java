package com.example.schoolmanagmentsystem.controller;

import com.example.schoolmanagmentsystem.dto.ClassDTO;
import com.example.schoolmanagmentsystem.dto.StudentDTO;
import com.example.schoolmanagmentsystem.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<ClassDTO> createClass(@Valid @RequestBody ClassDTO classDTO) {
        ClassDTO createdClass = classService.createClass(classDTO);
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        List<ClassDTO> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        ClassDTO classDTO = classService.getClassById(id);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @GetMapping("/{classId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByClassId(@PathVariable Long classId) {
        List<StudentDTO> students = classService.getStudentsByClassId(classId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Long id, @Valid @RequestBody ClassDTO classDTO) {
        ClassDTO updatedClass = classService.updateClass(id, classDTO);
        return new ResponseEntity<>(updatedClass, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassDTO> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
