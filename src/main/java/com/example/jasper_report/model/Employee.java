package com.example.jasper_report.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    private String name;
    private String department;

    @Column(name = "current_salary")
    private BigDecimal currentSalary;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<JobHistory> jobHistory;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<PerformanceEvaluation> performanceEvaluations;
}
