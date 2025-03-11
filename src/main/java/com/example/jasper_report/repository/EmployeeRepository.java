package com.example.jasper_report.repository;

import com.example.jasper_report.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);
    List<Employee> findByJoinDateBetween(LocalDate startDate, LocalDate endDate);
    List<Employee> findByDepartmentAndJoinDateBetween(String department, LocalDate startDate, LocalDate endDate);
}
