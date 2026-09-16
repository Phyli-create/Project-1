/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.payrollsystem;

/**
 *
 * @author emeris
 */
public class Remuneration {

    private int employeeId;
    private String companyName;
    private double hourlyRate;
    private double normalMonthlyWorkingHours;
    private double overtimeRate;

    // Constructor
    public Remuneration(int employeeId, String companyName,
                        double hourlyRate,
                        double normalMonthlyWorkingHours) {

        this.employeeId = employeeId;
        this.companyName = companyName;
        this.hourlyRate = hourlyRate;
        this.normalMonthlyWorkingHours = normalMonthlyWorkingHours;

        // Overtime is paid at 1.5 times the normal hourly rate
        this.overtimeRate = hourlyRate * 1.5;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getNormalMonthlyWorkingHours() {
        return normalMonthlyWorkingHours;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    // Calculate normal salary
    public double calculateNormalSalary(double hoursWorked) {

        double normalHours = Math.min(hoursWorked,
                                      normalMonthlyWorkingHours);

        return normalHours * hourlyRate;
    }

    // Calculate overtime hours
    public double calculateOvertimeHours(double hoursWorked) {

        if (hoursWorked > normalMonthlyWorkingHours) {
            return hoursWorked - normalMonthlyWorkingHours;
        }

        return 0;
    }

    // Determine whether employee worked overtime
    public boolean workedOvertime(double hoursWorked) {

        return hoursWorked > normalMonthlyWorkingHours;
    }

    // Calculate overtime salary
    public double calculateOvertimeSalary(double hoursWorked) {

        double overtimeHours = calculateOvertimeHours(hoursWorked);

        return overtimeHours * overtimeRate;
    }

    // Calculate total salary
    public double calculateTotalSalary(double hoursWorked) {

        return calculateNormalSalary(hoursWorked)
                + calculateOvertimeSalary(hoursWorked);
    }
}