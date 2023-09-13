package com.example.FinalProjectIT.repository;

import com.example.FinalProjectIT.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    static final List<Employee> employeeList = new ArrayList<>(List.of(new Employee(1L, "Alex", LocalDate.of(1995, 3, 20)),
            new Employee(2L, "Albu", LocalDate.of(1992, 6, 6)),
            new Employee(3L, "Neag", LocalDate.of(1990, 7, 24)),
            new Employee(4L, "Popescu", LocalDate.of(1980, 1, 1))));

    public default List<Employee> findAllEmployee() {
        return employeeList;
    }


    public default Optional<Employee> findEmployeebyId(int id) {
        return employeeList.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    public List<Employee> findEmployeeByName(String name);

    public default List<Employee> findEmployeesBornAfterDate(LocalDate date) {
        return employeeList.stream().filter(employee -> employee.getDate().isAfter(date)).collect(Collectors.toList());
    }

    public default Employee addStudent(Employee employee) {
        employeeList.add(employee);
        return employeeList.get(employeeList.size() - 1);
    }

    public default Boolean updateEmployee(int id, Map<String, Object> params) {
        Optional<Employee> optionalEmployee = findEmployeebyId(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (params.containsKey("name")) {
                employee.setName((String) params.get("name"));
            } else if (params.containsKey("date")) {
                employee.setDate(LocalDate.parse((String) params.get("date")));
            }
            return true;
        } else {
            return false;
        }
    }
}