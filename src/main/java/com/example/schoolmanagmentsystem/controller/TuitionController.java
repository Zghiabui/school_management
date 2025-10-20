package com.example.schoolmanagmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.service.TuitionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tuitions")
public class TuitionController {

    @Autowired
    private TuitionService tuitionService;

    @GetMapping
    public List<TuitionDTO> getAllTuitions() {
        return tuitionService.getAllTuitions();
    }

}
