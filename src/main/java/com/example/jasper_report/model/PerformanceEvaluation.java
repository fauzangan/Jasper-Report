package com.example.jasper_report.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "performance_evaluations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int year;
    private BigDecimal rating;
    private String feedback;

    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;
}
