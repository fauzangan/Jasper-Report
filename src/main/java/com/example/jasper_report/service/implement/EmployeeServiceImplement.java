package com.example.jasper_report.service.implement;

import com.example.jasper_report.model.Employee;
import com.example.jasper_report.model.JobHistory;
import com.example.jasper_report.model.PerformanceEvaluation;
import com.example.jasper_report.repository.EmployeeRepository;
import com.example.jasper_report.repository.JobHistoryRepository;
import com.example.jasper_report.repository.PerformanceEvaluationRepository;
import com.example.jasper_report.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplement implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final PerformanceEvaluationRepository performanceEvaluationRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByJoinDateBetween(startDate, endDate);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentAndDateRange(String department, LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByDepartmentAndJoinDateBetween(department, startDate, endDate);
    }

    @Override
    public List<JobHistory> getJobHistoryByEmployeeId(Long employeeId) {
        return jobHistoryRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<PerformanceEvaluation> getPerformanceEvaluationsByEmployeeId(Long employeeId) {
        return performanceEvaluationRepository.findByEmployeeId(employeeId);
    }
}
