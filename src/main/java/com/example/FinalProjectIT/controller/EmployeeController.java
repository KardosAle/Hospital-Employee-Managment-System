package com.example.FinalProjectIT.controller;

import com.example.FinalProjectIT.dao.Employee;
import com.example.FinalProjectIT.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getEmployee(@RequestParam Map<String, String> params) {
        return employeeService.getEmployee(params);

    }

  @GetMapping("/{id}")
    public Employee getEmployeetById(@PathVariable Integer id) {
        return employeeService.getEmployeeById((long) id);
    }

    @PostMapping
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id,
                                                @RequestBody Employee employee) {
        if(employeeService.updateEmployee(id, employee).equals(employee))  {
            return new ResponseEntity<>("Employee with ID: " + id + " was updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Employee with ID: " + id + ". No Employee was found.",
                    HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id){
        ResponseEntity<Object> response = null;
        try {
            boolean deleted = employeeService.deleteEmployee(id);
            if(!deleted) {
                response =  new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
            } else {
                response =  new ResponseEntity<>("employee with ID: " + id + " was deleted successfully!", HttpStatus.OK);
            }
        } catch (Exception e){
            response = new ResponseEntity<>("400", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}

