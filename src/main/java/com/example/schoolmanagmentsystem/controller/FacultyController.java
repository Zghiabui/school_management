package com.example.schoolmanagmentsystem.controller;

import com.example.schoolmanagmentsystem.dto.FacultyDTO;
import com.example.schoolmanagmentsystem.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/faculties")
@CrossOrigin(origins = "*")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@Valid @RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = facultyService.createFaculty(facultyDTO);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable Long id, @Valid @RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updatedFaculty = facultyService.updateFaculty(id, facultyDTO);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultyDTO> deleteFaculty(@PathVariable Long id) {
        FacultyDTO deletedFaculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(deletedFaculty);
    }
}