package com.example.schoolmanagmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.service.TuitionService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tuitions")
public class TuitionController {

    @Autowired
    private TuitionService tuitionService;

    @PostMapping
    public ResponseEntity<TuitionDTO> createTuition(@Valid @RequestBody TuitionDTO tuitionDTO) {
        TuitionDTO createdTuition = tuitionService.createTuition(tuitionDTO);
        return new ResponseEntity<>(createdTuition, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TuitionDTO>> getAllTuitions() {
        List<TuitionDTO> tuitions = tuitionService.getAllTuitions();
        return new ResponseEntity<>(tuitions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TuitionDTO> getTuitionById(@PathVariable Long id) {
        TuitionDTO tuition = tuitionService.getTuitionById(id);
        return new ResponseEntity<>(tuition, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TuitionDTO> updateTuition(@PathVariable Long id, @Valid @RequestBody TuitionDTO tuitionDTO) {
        TuitionDTO updatedTuition = tuitionService.updateTuition(id, tuitionDTO);
        return new ResponseEntity<>(updatedTuition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTuition(@PathVariable Long id) {
        tuitionService.deleteTuition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
