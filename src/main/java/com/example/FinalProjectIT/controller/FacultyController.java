package com.example.FinalProjectIT.controller;


import com.example.FinalProjectIT.dao.Faculty;
import com.example.FinalProjectIT.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    FacultyService facultyService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getFaculties(@RequestParam Map<String, Object> params) {
        return facultyService.getFaculties(params);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addFaculty(@Valid @RequestBody Faculty faculty) {
        return new ResponseEntity<>(facultyService.addFaculty(faculty), HttpStatus.CREATED);
    }
}
