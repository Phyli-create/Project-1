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
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // CREATE COMPANIES
        // ==========================================

        Company company1 = new Company(
                101,
                "ABC Technologies",
                "Durban"
        );

        Company company2 = new Company(
                102,
                "XYZ Solutions",
                "Johannesburg"
        );


        // ==========================================
        // CREATE EMPLOYEES
        // ==========================================

        Employee employee1 = new Employee(
                1001,
                "John",
                "Smith",
                "12 Beach Road, Durban",
                company1,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 12, 31)
        );

        Employee employee2 = new Employee(
                1002,
                "Sarah",
                "Naidoo",
                "25 Main Street, Durban",
                company1,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 12, 31)
        );

        Employee employee3 = new Employee(
                1003,
                "David",
                "Mokoena",
                "45 Church Street, Johannesburg",
                company2,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 12, 31)
        );


        // ==========================================
        // CREATE REMUNERATION INFORMATION
        // ==========================================

        Remuneration remuneration1 = new Remuneration(
                employee1.getEmployeeId(),
                company1.getCompanyName(),
                150.00,
                160
        );

        Remuneration remuneration2 = new Remuneration(
                employee2.getEmployeeId(),
                company1.getCompanyName(),
                120.00,
                160
        );

        Remuneration remuneration3 = new Remuneration(
                employee3.getEmployeeId(),
                company2.getCompanyName(),
                200.00,
                160
        );


        // ==========================================
        // COLLECTION OF EMPLOYEES
        // ==========================================

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);


        // ==========================================
        // COLLECTION OF SALARY RECORDS
        // ==========================================

        ArrayList<Salary> salaryRecords = new ArrayList<>();


        // ==========================================
        // SIX MONTHS OF SALARY DATA
        // ==========================================

        // Employee 1
        salaryRecords.add(new Salary(
                "January",
                employee1,
                company1,
                160,
                remuneration1
        ));

        salaryRecords.add(new Salary(
                "February",
                employee1,
                company1,
                170,
                remuneration1
        ));

        salaryRecords.add(new Salary(
                "March",
                employee1,
                company1,
                165,
                remuneration1
        ));

        salaryRecords.add(new Salary(
                "April",
                employee1,
                company1,
                160,
                remuneration1
        ));

        salaryRecords.add(new Salary(
                "May",
                employee1,
                company1,
                180,
                remuneration1
        ));

        salaryRecords.add(new Salary(
                "June",
                employee1,
                company1,
                175,
                remuneration1
        ));


        // Employee 2
        salaryRecords.add(new Salary(
                "January",
                employee2,
                company1,
                160,
                remuneration2
        ));

        salaryRecords.add(new Salary(
                "February",
                employee2,
                company1,
                160,
                remuneration2
        ));

        salaryRecords.add(new Salary(
                "March",
                employee2,
                company1,
                168,
                remuneration2
        ));

        salaryRecords.add(new Salary(
                "April",
                employee2,
                company1,
                172,
                remuneration2
        ));

        salaryRecords.add(new Salary(
                "May",
                employee2,
                company1,
                160,
                remuneration2
        ));

        salaryRecords.add(new Salary(
                "June",
                employee2,
                company1,
                166,
                remuneration2
        ));


        // Employee 3
        salaryRecords.add(new Salary(
                "January",
                employee3,
                company2,
                170,
                remuneration3
        ));

        salaryRecords.add(new Salary(
                "February",
                employee3,
                company2,
                175,
                remuneration3
        ));

        salaryRecords.add(new Salary(
                "March",
                employee3,
                company2,
                160,
                remuneration3
        ));

        salaryRecords.add(new Salary(
                "April",
                employee3,
                company2,
                185,
                remuneration3
        ));

        salaryRecords.add(new Salary(
                "May",
                employee3,
                company2,
                180,
                remuneration3
        ));

        salaryRecords.add(new Salary(
                "June",
                employee3,
                company2,
                165,
                remuneration3
        ));


        // ==========================================
        // DISPLAY EMPLOYEE INFORMATION
        // ==========================================

        System.out.println("==============================================");
        System.out.println("          EMPLOYEE INFORMATION");
        System.out.println("==============================================");

        for (Employee employee : employees) {

            employee.displayEmployeeDetails();

            System.out.println("----------------------------------------------");
        }


        // ==========================================
        // SIX-MONTH SARS REMUNERATION REPORT
        // ==========================================

        System.out.println("\n\n==============================================");
        System.out.println("       SIX-MONTH SARS REMUNERATION REPORT");
        System.out.println("==============================================");


        // Loop through each employee
        for (Employee employee : employees) {

            System.out.println("\nEmployee: "
                    + employee.getName()
                    + " "
                    + employee.getSurname());

            System.out.println("Employee ID: "
                    + employee.getEmployeeId());

            System.out.println("Company: "
                    + employee.getCompany().getCompanyName());

            System.out.println("----------------------------------------------");

            System.out.printf(
                    "%-10s %-15s %10s %12s %12s %12s%n",
                    "Month",
                    "Employee",
                    "Hours",
                    "Normal Pay",
                    "OT Hours",
                    "OT Pay"
            );

            System.out.println(
                    "---------------------------------------------------------------"
            );


            // Six-month totals
            double totalNormalSalary = 0;
            double totalOvertimeHours = 0;
            double totalOvertimeEarnings = 0;
            double totalGrossSalary = 0;

            boolean workedOvertimeDuringPeriod = false;


            // Loop through salary records
            for (Salary salary : salaryRecords) {

                // Only display records belonging to current employee
                if (salary.getEmployee().getEmployeeId()
                        == employee.getEmployeeId()) {

                    salary.displaySalary();

                    // Add to totals
                    totalNormalSalary += salary.getNormalSalary();

                    totalOvertimeHours += salary.getOvertimeHours();

                    totalOvertimeEarnings +=
                            salary.getOvertimeSalary();

                    totalGrossSalary +=
                            salary.getTotalSalary();


                    // Conditional statement
                    if (salary.getOvertimeHours() > 0) {
                        workedOvertimeDuringPeriod = true;
                    }
                }
            }


            // ==========================================
            // SIX-MONTH TOTALS
            // ==========================================

            System.out.println(
                    "---------------------------------------------------------------"
            );

            System.out.printf(
                    "Total Normal Salary:     R%.2f%n",
                    totalNormalSalary
            );

            System.out.printf(
                    "Total Overtime Hours:    %.2f hours%n",
                    totalOvertimeHours
            );

            System.out.printf(
                    "Total Overtime Earnings: R%.2f%n",
                    totalOvertimeEarnings
            );

            System.out.printf(
                    "Total Gross Salary:      R%.2f%n",
                    totalGrossSalary
            );


            if (workedOvertimeDuringPeriod) {

                System.out.println(
                        "Worked Overtime:         YES"
                );

            } else {

                System.out.println(
                        "Worked Overtime:         NO"
                );
            }

            System.out.println(
                    "==============================================================="
            );
        }


        // ==========================================
        // PROGRAM COMPLETE
        // ==========================================

        System.out.println("\nReport generated successfully.");
    }
}
