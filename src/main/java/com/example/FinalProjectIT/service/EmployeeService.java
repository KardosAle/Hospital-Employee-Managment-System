package com.example.FinalProjectIT.service;

import com.example.FinalProjectIT.dao.Employee;
import com.example.FinalProjectIT.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private static final String ALL = "all";

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployee(Map<String, String> params) {

        if (params.size() == 0) {
            return employeeRepository.findAll();
        }
        else if(params.containsKey(("name"))){
            return employeeRepository.findEmployeeByName(params.get("name"));
        }
        else{
            return null;
        }
        // if ("true".equalsIgnoreCase(params.get(ALL))) {
//                return employeeRepository.findAllEmployee();
//            } else if (params.containsKey("name")) {
//                return employeeRepository.findEmployeeByName(params.get("name"));
//            } else {
//                return null;
//            }
//        }
    }

    public Employee getEmployeeById(Long id) {
        Employee st = employeeRepository.getReferenceById(id);
        return st;
    }

    //
//
//    public Employee getEmployeeById(Long id) {
//        return employeeRepository.findEmployeebyId(id).orElse(null);
//    }
//
//    public List<Employee> getEmployeesByName(String name) {
//        return employeeRepository.findEmployeeByName(name);
//    }
//
//    public List<Employee> getEmployeesBornAfterDate(String date) {
//        return employeeRepository.findEmployeesBornAfterDate(LocalDate.parse(date));
//    }
//
    public Employee addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }


    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setDate(updatedEmployee.getDate());
            return employeeRepository.saveAndFlush(employee);
        }).orElse(null);
    }
//    public Boolean updateEmployee(Long id, Employee employee) {
//        //verify Params
//
//        return employeeRepository.updateEmployee(id);
//    }

    public Boolean deleteEmployee(Long employeeId) {

        List<Employee> EmployeeList = employeeRepository.findAll();

        if (EmployeeList.stream().anyMatch(employee -> employee.getId().equals(employeeId))) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

}
