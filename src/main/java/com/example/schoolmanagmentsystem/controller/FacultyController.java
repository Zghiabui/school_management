package com.example.schoolmanagmentsystem.controller;

import com.example.schoolmanagmentsystem.dto.FacultyDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@Valid @RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = facultyService.createFaculty(facultyDTO);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FacultyDTO> getAllFaculties() {
        return facultyService.getAllFaculty();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable Long id, @RequestBody FacultyDTO facultyDTO) {

        FacultyDTO updatedFaculty = facultyService.updateFaculty(id, facultyDTO);

        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
