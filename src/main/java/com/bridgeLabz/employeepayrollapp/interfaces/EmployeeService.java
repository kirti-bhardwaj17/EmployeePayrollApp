package com.bridgeLabz.employeepayrollapp.interfaces;

import com.bridgeLabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgeLabz.employeepayrollapp.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(EmployeeDTO employeeDTO);
    Optional<Employee> updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO);
    boolean deleteEmployee(Long id);
}
