package com.bridgeLabz.employeepayrollapp.interfaces;

import com.bridgeLabz.employeepayrollapp.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(Employee employee);  // Use Employee instead of EmployeeDTO
    Optional<Employee> updateEmployee(Long id, Employee updatedEmployee);  // Use Employee instead of EmployeeDTO
    boolean deleteEmployee(Long id);
}
