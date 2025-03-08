package com.bridgeLabz.employeepayrollapp.service;

import com.bridgeLabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgeLabz.employeepayrollapp.model.Employee;
import com.bridgeLabz.employeepayrollapp.repository.EmployeeRepository;
import com.bridgeLabz.employeepayrollapp.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // ✅ Constructor-based dependency injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        // ✅ Convert DTO to Entity before saving
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment("General"); // Default department if missing

        // ✅ Save employee to database
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        return employeeRepository.findById(id).map(existing -> {
            existing.setName(updatedEmployeeDTO.getName());
            existing.setSalary(updatedEmployeeDTO.getSalary());
            Employee updated = employeeRepository.save(existing);
            return updated;
        });
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
