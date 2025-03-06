package com.bridgeLabz.employeepayrollapp.service;

import com.bridgeLabz.employeepayrollapp.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final List<EmployeeDTO> employeeList = new ArrayList<>();
    private long idCounter = 1;

    public List<EmployeeDTO> getAllEmployees() {
        return new ArrayList<>(employeeList);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId(idCounter++);
        employeeList.add(employeeDTO);
        return employeeDTO;
    }

    public Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .map(emp -> {
                    emp.setName(updatedEmployeeDTO.getName());
                    emp.setSalary(updatedEmployeeDTO.getSalary());
                    return emp;
                });
    }

    public boolean deleteEmployee(Long id) {
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
