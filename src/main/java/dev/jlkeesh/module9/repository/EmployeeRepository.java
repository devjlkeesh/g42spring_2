package dev.jlkeesh.module9.repository;

import dev.jlkeesh.module9.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}