package com.example.jasper_report.service;

import java.time.LocalDate;

public interface ReportService {
    byte[] generateEmployeeReport(String department, LocalDate startDate, LocalDate endDate) throws Exception;
    byte[] generateEmployeeReportById(Long employeeId) throws Exception;
}
