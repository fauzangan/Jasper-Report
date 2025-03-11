package com.example.jasper_report.repository;

import com.example.jasper_report.model.JobHistory;
import com.example.jasper_report.model.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {
    List<PerformanceEvaluation> findByEmployeeId(Long employeeId);
}
