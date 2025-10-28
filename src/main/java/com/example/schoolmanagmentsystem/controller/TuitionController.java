package com.example.schoolmanagmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.service.TuitionService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
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
        return new ResponseEntity<>(tuitionService.getAllTuitions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TuitionDTO> getTuitionById(@PathVariable Long id) {
        return new ResponseEntity<>(tuitionService.getTuitionById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TuitionDTO> updateTuition(@PathVariable Long id,
                                                    @Valid @RequestBody TuitionDTO tuitionDTO) {
        return new ResponseEntity<>(tuitionService.updateTuition(id, tuitionDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTuition(@PathVariable Long id) {
        tuitionService.deleteTuition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
