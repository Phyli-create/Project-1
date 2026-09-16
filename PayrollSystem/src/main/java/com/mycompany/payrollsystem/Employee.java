/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.payrollsystem;

/**
 *
 * @author emeris
 */
import java.time.LocalDate;

public class Employee extends Person {

    private int employeeId;
    private Company company;
    private LocalDate employmentStartDate;
    private LocalDate employmentEndDate;

    // Constructor
    public Employee(int employeeId, String name, String surname,
                    String address, Company company,
                    LocalDate employmentStartDate,
                    LocalDate employmentEndDate) {

        super(name, surname, address);

        this.employeeId = employeeId;
        this.company = company;
        this.employmentStartDate = employmentStartDate;
        this.employmentEndDate = employmentEndDate;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public Company getCompany() {
        return company;
    }

    public LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public LocalDate getEmploymentEndDate() {
        return employmentEndDate;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setEmploymentStartDate(LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public void setEmploymentEndDate(LocalDate employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    public void displayEmployeeDetails() {

        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Address: " + getAddress());
        System.out.println("Company: " + company.getCompanyName());
        System.out.println("Employment Start Date: " + employmentStartDate);
        System.out.println("Employment End Date: " + employmentEndDate);
    }
}