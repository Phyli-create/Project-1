/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.payrollsystem;

/**
 *
 * @author emeris
 */
public class Salary {

    private String month;
    private Employee employee;
    private Company company;
    private double hoursWorked;

    private double normalSalary;
    private double overtimeHours;
    private double overtimeSalary;
    private double totalSalary;

    // Constructor
    public Salary(String month, Employee employee,
                  Company company, double hoursWorked,
                  Remuneration remuneration) {

        this.month = month;
        this.employee = employee;
        this.company = company;
        this.hoursWorked = hoursWorked;

        // Calculate salary using Remuneration class
        this.normalSalary =
                remuneration.calculateNormalSalary(hoursWorked);

        this.overtimeHours =
                remuneration.calculateOvertimeHours(hoursWorked);

        this.overtimeSalary =
                remuneration.calculateOvertimeSalary(hoursWorked);

        this.totalSalary =
                remuneration.calculateTotalSalary(hoursWorked);
    }

    // Getters
    public String getMonth() {
        return month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Company getCompany() {
        return company;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getNormalSalary() {
        return normalSalary;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public double getOvertimeSalary() {
        return overtimeSalary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    // Display monthly salary
    public void displaySalary() {

        System.out.printf(
                "%-10s %-15s %10.2f %12.2f %12.2f %12.2f%n",
                month,
                employee.getName(),
                hoursWorked,
                normalSalary,
                overtimeHours,
                overtimeSalary
        );
    }
}
