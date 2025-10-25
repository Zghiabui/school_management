package com.example.schoolmanagmentsystem.controller;

import com.example.schoolmanagmentsystem.dto.FacultyDTO;
// import com.example.schoolmanagmentsystem.entity.Faculty; // (Không cần import này)
import com.example.schoolmanagmentsystem.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties") // <-- LỖI CHÍNH ĐÃ SỬA
@CrossOrigin(origins = "http://localhost:3000") // Đảm bảo React của bạn chạy ở port 3000
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@Valid @RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = facultyService.createFaculty(facultyDTO);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() { // <-- Nên trả về ResponseEntity
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable Long id,
                                                    @Valid @RequestBody FacultyDTO facultyDTO) { // <-- THÊM @Valid
        FacultyDTO updatedFaculty = facultyService.updateFaculty(id, facultyDTO);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultyDTO> deleteFaculty(@PathVariable Long id) { // <-- SỬA: Trả về DTO
        FacultyDTO deletedFaculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(deletedFaculty); // <-- SỬA: Trả về đối tượng đã xóa
    }
}