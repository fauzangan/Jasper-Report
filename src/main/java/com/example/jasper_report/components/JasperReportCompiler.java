package com.example.jasper_report.components;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JasperReportCompiler {

    private static final String REPORTS_DIR = "src/main/resources/reports/";

    @PostConstruct
    public void compileReports() {
        try {
            compileReport("employee_report.jrxml");
            compileReport("job_history_subreport.jrxml");
            compileReport("performance_evaluation_subreport.jrxml");
            compileReport("salary_trends_subreport.jrxml");
            compileReport("department_summary_subreport.jrxml");
            System.out.println("All reports compiled successfully!");
        } catch (Exception e) {
            System.err.println("Error compiling reports: " + e.getMessage());
        }
    }

    private void compileReport(String reportFile) throws Exception {
        String sourceFilePath = REPORTS_DIR + reportFile;
        String destFilePath = REPORTS_DIR + reportFile.replace(".jrxml", ".jasper");

        File file = new File(sourceFilePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + sourceFilePath);
        }

        JasperCompileManager.compileReportToFile(sourceFilePath, destFilePath);
        System.out.println("Compiled: " + reportFile);
    }
}
