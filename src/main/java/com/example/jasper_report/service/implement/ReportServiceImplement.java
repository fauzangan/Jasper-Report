package com.example.jasper_report.service.implement;

import com.example.jasper_report.model.Employee;
import com.example.jasper_report.service.EmployeeService;
import com.example.jasper_report.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImplement implements ReportService {
    private final EmployeeService employeeService;
    private final DataSource dataSource;
    @Value("${jasper.report.path}")
    private String reportPath;

    @Override
    public byte[] generateEmployeeReport(String department, LocalDate startDate, LocalDate endDate) throws Exception {
        try {
//            JasperReport mainReport = JasperCompileManager.compileReport(
//                    ResourceUtils.getFile(reportPath + "employee_report.jrxml").getAbsolutePath()
//            );
            InputStream mainReportStream = getClass().getResourceAsStream("/reports/employee_report.jasper");
            JasperReport mainReport = (JasperReport) JRLoader.loadObject(mainReportStream);

//            JasperReport jobHistorySubReport = JasperCompileManager.compileReport(
//                    ResourceUtils.getFile(reportPath + "job_history_subreport.jrxml").getAbsolutePath()
//            );
//            JasperReport performanceSubReport = JasperCompileManager.compileReport(
//                    ResourceUtils.getFile(reportPath + "performance_evaluation_subreport.jrxml").getAbsolutePath()
//            );
//            JasperReport salaryTrensSubReport = JasperCompileManager.compileReport(
//                    ResourceUtils.getFile(reportPath + "salary_trends_subreport.jrxml").getAbsolutePath()
//            );
//            JasperReport departmentSummarySubReport = JasperCompileManager.compileReport(
//                    ResourceUtils.getFile(reportPath + "department_summary_subreport.jrxml").getAbsolutePath()
//            );

            InputStream jobHistoryStream = getClass().getResourceAsStream("/reports/job_history_subreport.jasper");
            JasperReport jobHistorySubReport = (JasperReport) JRLoader.loadObject(jobHistoryStream);

            InputStream performanceStream = getClass().getResourceAsStream("/reports/performance_evaluation_subreport.jasper");
            JasperReport performanceSubReport = (JasperReport) JRLoader.loadObject(performanceStream);

            InputStream salaryTrendsStream = getClass().getResourceAsStream("/reports/salary_trends_subreport.jasper");
            JasperReport salaryTrendsSubReport = (JasperReport) JRLoader.loadObject(salaryTrendsStream);

            InputStream departmentSummaryStream = getClass().getResourceAsStream("/reports/department_summary_subreport.jasper");
            JasperReport departmentSummarySubReport = (JasperReport) JRLoader.loadObject(departmentSummaryStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());
            parameters.put("departementParam", department);
            parameters.put("startDateParam", startDate != null ? java.sql.Date.valueOf(startDate) : null);
            parameters.put("endDateParam", endDate != null ? java.sql.Date.valueOf(endDate) : null);


            List<Employee> employees;
            if (department != null && startDate != null && endDate != null) {
                employees = employeeService.getEmployeesByDepartmentAndDateRange(department, startDate, endDate);
            } else if (department != null) {
                employees = employeeService.getEmployeesByDepartment(department);
            } else if (startDate != null && endDate != null) {
                employees = employeeService.getEmployeesByDateRange(startDate, endDate);
            } else {
                employees = employeeService.getAllEmployees();
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e){
            throw new Exception("Error generating employee report: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] generateEmployeeReportById(Long employeeId) throws Exception {
        try {
            InputStream mainReportStream = getClass().getResourceAsStream("/reports/employee_report.jasper");
            JasperReport mainReport = (JasperReport) JRLoader.loadObject(mainReportStream);

            InputStream jobHistoryStream = getClass().getResourceAsStream("/reports/job_history_subreport.jasper");
            JasperReport jobHistorySubReport = (JasperReport) JRLoader.loadObject(jobHistoryStream);

            InputStream performanceStream = getClass().getResourceAsStream("/reports/performance_evaluation_subreport.jasper");
            JasperReport performanceSubReport = (JasperReport) JRLoader.loadObject(performanceStream);

            InputStream salaryTrendsStream = getClass().getResourceAsStream("/reports/salary_trends_subreport.jasper");
            JasperReport salaryTrendsSubReport = (JasperReport) JRLoader.loadObject(salaryTrendsStream);

            InputStream departmentSummaryStream = getClass().getResourceAsStream("/reports/department_summary_subreport.jasper");
            JasperReport departmentSummarySubReport = (JasperReport) JRLoader.loadObject(departmentSummaryStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());

            // Get a single employee by ID
            Employee employee = employeeService.getEmployeeById(employeeId);

            // Create a list with just this one employee
            List<Employee> employees = Collections.singletonList(employee);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e){
            throw new Exception("Error generating employee report by ID: " + e.getMessage(), e);
        }
    }
}
