package com.bridgeLabz.employeepayrollapp.controller;

import com.bridgeLabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgeLabz.employeepayrollapp.model.Employee;
import com.bridgeLabz.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(emp -> ResponseEntity.ok(new EmployeeDTO(emp.getName(), emp.getSalary())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Create new employee
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary(), "Default Dept");
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(savedEmployee.getName(), savedEmployee.getSalary());
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployeeDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployeeDTO.getName());
                    employee.setSalary(updatedEmployeeDTO.getSalary());
                    Employee savedEmployee = employeeRepository.save(employee);
                    return ResponseEntity.ok(new EmployeeDTO(savedEmployee.getName(), savedEmployee.getSalary()));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Remove employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
