package com.example.jasper_report.repository;

import com.example.jasper_report.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    List<JobHistory> findByEmployeeId(Long id);
}
