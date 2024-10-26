package dev.jlkeesh.module9.controller;

import dev.jlkeesh.module9.dto.employee.EmployeeCreateDto;
import dev.jlkeesh.module9.entity.Employee;
import dev.jlkeesh.module9.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public Employee create(@Valid @RequestBody EmployeeCreateDto dto){
        Employee employee = new Employee();
        employee.setAge(dto.age());
        employee.setName(dto.name());
        employee.setSurname(dto.surname());
        return employeeRepository.save(employee);
    }
}
