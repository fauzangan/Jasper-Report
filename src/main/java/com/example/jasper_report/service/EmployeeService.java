package com.example.jasper_report.service;

import com.example.jasper_report.model.Employee;
import com.example.jasper_report.model.JobHistory;
import com.example.jasper_report.model.PerformanceEvaluation;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate);
    List<Employee> getEmployeesByDepartmentAndDateRange(String department, LocalDate startDate, LocalDate endDate);
    List<JobHistory> getJobHistoryByEmployeeId(Long employeeId);
    List<PerformanceEvaluation> getPerformanceEvaluationsByEmployeeId(Long employeeId);

}
