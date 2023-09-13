package com.example.FinalProjectIT.repository;

import com.example.FinalProjectIT.dao.Faculty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FacultyRepository {

    private static final List<Faculty> facultyList = new ArrayList<>(List.of(new Faculty(1, "ACS"),
            new Faculty(2, "Albu"),
            new Faculty(3, "Neag"),
            new Faculty(4, "Popescu")));

    public List<Faculty> findAllFaculties() {
        return facultyList;
    }

    public Optional<Faculty> findFacultybyId(int id) {
        return facultyList.stream().filter(employee-> employee.getId() == id).findFirst();
    }

    public List<Faculty> findFacultyByName(String name) {
        return facultyList.stream().filter(student -> Objects.equals(student.getName(), name)).collect(Collectors.toList());
    }

    public Faculty addFaculty(Faculty faculty) {
        facultyList.add(faculty);
        return facultyList.get(facultyList.size() - 1);
    }


}
