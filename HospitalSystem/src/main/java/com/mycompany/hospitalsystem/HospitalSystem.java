/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalsystem;

import java.util.Scanner;

public class HospitalSystem {

    private static PatientManager manager = new PatientManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            displayMenu();
            choice = getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    manager.displayAllPatients();
                    break;

                case 6:
                    allocateBed();
                    break;

                case 7:
                    releaseBed();
                    break;

                case 8:
                    manager.displayWardLayout();
                    break;

                case 9:
                    manager.displayAvailableBeds();
                    break;

                case 10:
                    manager.displayOccupiedBeds();
                    break;

                case 11:
                    displayReports();
                    break;

                case 12:
                    sortPatients();
                    break;

                case 0:
                    System.out.println("Exiting Hospital Patient Admission System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n======================================");
        System.out.println("   HOSPITAL PATIENT ADMISSION SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");
        System.out.println("6. Allocate Bed");
        System.out.println("7. Release Bed");
        System.out.println("8. Display Ward Layout");
        System.out.println("9. Display Available Beds");
        System.out.println("10. Display Occupied Beds");
        System.out.println("11. Reports");
        System.out.println("12. Sort Patients");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    private static void registerPatient() {

        System.out.println("\n--- Register Patient ---");

        String id = getString("Patient ID: ");

        if (manager.searchPatient(id) != null) {
            System.out.println("Error: Patient ID already exists.");
            return;
        }

        String firstName = getString("First Name: ");
        String lastName = getString("Last Name: ");
        int age = getInt("Age: ");
        String gender = getString("Gender: ");
        String condition = getString("Medical Condition: ");

        System.out.println("Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        int categoryChoice = getInt("Choose category: ");

        Patient patient;

        switch (categoryChoice) {

            case 1:
                patient = new Inpatient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        0,
                        null
                );
                break;

            case 2:
                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.OUTPATIENT
                );
                break;

            case 3:
                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.EMERGENCY
                );
                break;

            default:
                System.out.println("Invalid patient category.");
                return;
        }

        if (manager.registerPatient(patient)) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("Patient registration failed.");
        }
    }

    private static void searchPatient() {

        System.out.println("\n--- Search Patient ---");

        String id = getString("Enter Patient ID: ");

        Patient patient = manager.searchPatient(id);

        if (patient != null) {
            System.out.println("\nPatient found:");
            System.out.println("----------------------------");
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {

        System.out.println("\n--- Update Patient ---");

        String id = getString("Enter Patient ID: ");

        Patient patient = manager.searchPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String firstName = getString("New First Name: ");
        String lastName = getString("New Last Name: ");
        int age = getInt("New Age: ");
        String gender = getString("New Gender: ");
        String condition = getString("New Medical Condition: ");

        if (manager.updatePatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition)) {

            System.out.println("Patient updated successfully.");

        } else {
            System.out.println("Patient update failed.");
        }
    }

    private static void deletePatient() {

        System.out.println("\n--- Delete Patient ---");

        String id = getString("Enter Patient ID: ");

        if (manager.deletePatient(id)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void allocateBed() {

        System.out.println("\n--- Allocate Bed ---");

        String id = getString("Enter Inpatient ID: ");

        Patient patient = manager.searchPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        if (!(patient instanceof Inpatient)) {
            System.out.println("Only Inpatients may be allocated a bed.");
            return;
        }

        String bedNumber = getString("Enter bed number (B01-B20): ");

        if (manager.allocateBed(id, bedNumber)) {
            System.out.println("Bed allocated successfully.");
        } else {
            System.out.println("Bed allocation failed.");
            System.out.println("Check that the bed exists and is available.");
        }
    }

    private static void releaseBed() {

        System.out.println("\n--- Release Bed ---");

        String id = getString("Enter Inpatient ID: ");

        if (manager.releaseBed(id)) {
            System.out.println("Bed released successfully.");
        } else {
            System.out.println("Unable to release bed.");
        }
    }

    private static void displayReports() {

        System.out.println("\n========== HOSPITAL REPORTS ==========");

        System.out.println("Total registered patients: "
                + manager.getTotalPatients());

        System.out.println("Total occupied beds: "
                + manager.getOccupiedBeds());

        System.out.println("Total available beds: "
                + (20 - manager.getOccupiedBeds()));

        System.out.printf("Ward occupancy: %.2f%%%n",
                manager.getOccupancyPercentage());

        System.out.println("======================================");
    }

    private static void sortPatients() {

        System.out.println("\n--- Sort Patients ---");
        System.out.println("1. Sort by surname");
        System.out.println("2. Sort by Patient ID");

        int choice = getInt("Choose sorting option: ");

        switch (choice) {

            case 1:
                manager.sortBySurname();
                System.out.println("Patients sorted by surname.");
                manager.displayAllPatients();
                break;

            case 2:
                manager.sortByPatientId();
                System.out.println("Patients sorted by Patient ID.");
                manager.displayAllPatients();
                break;

            default:
                System.out.println("Invalid sorting option.");
        }
    }

    private static String getString(String message) {

        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int getInt(String message) {

        while (true) {

            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}
