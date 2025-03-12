# Employee Report System with JasperReports and Spring Boot

This project implements a detailed multi-section PDF report for employee records using JasperReports and Spring Boot.

## Prerequisites

- Java 17
- Maven 3.6
- PostgreSQL 12

## Setup

1. Create a PostgreSQL database named `jasper_report`
   ```sql
   CREATE DATABASE jasper_report;
   ```
2. Configure the database connection in `src/main/resources/application.properties`
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/jasper_report
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
## Running the Application

1. Build the project:
   ```
   mvn clean 
   ```
2. Run the application:
   ```
   mvn spring-boot:run
   ```
   
## API Endpoints

- Generate report for all employees:
  ```
  GET /api/reports/employees
  ```
- Generate report filtered by department:
  ```  
  GET /api/reports/employees?department=IT
  ```

- Generate report filtered by date range:
  ```
  GET /api/reports/employees?startDate=2020-01-01&endDate=2023-12-31
  ```
  
- Generate report filtered by department and date range:
  ```
  GET /api/reports/employees?department=IT&startDate=2020-01-01&endDate=2023-12-31
  ```
  
- Generate report for a specific employee:
  ```
  GET /api/reports/employees/{employeeId}
  ```
  
## Result