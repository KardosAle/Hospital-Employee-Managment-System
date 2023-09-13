package com.example.FinalProjectIT.service;

import com.example.FinalProjectIT.dao.Faculty;
import com.example.FinalProjectIT.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private static final String ALL = "all";

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getFaculties(Map<String, Object> params) {
        List<Faculty> facultyList = null;
        if("true".equalsIgnoreCase((String) params.get(ALL))) {
            facultyList = facultyRepository.findAllFaculties();
        } else if (params.containsKey("id")){
            Optional<Faculty> optionalFaculty = facultyRepository.findFacultybyId(Integer.parseInt((String) params.get("id")));
            facultyList = optionalFaculty.map(List::of).orElseGet(ArrayList::new);
        }
        return facultyList;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.addFaculty(faculty);
    }

    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findFacultybyId(id).orElse(null);
    }

    public List<Faculty> getFacultiesByName(String name) {
        return facultyRepository.findFacultyByName(name);
    }
}
