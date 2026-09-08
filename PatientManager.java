/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalsystem;

import java.util.ArrayList;
import java.util.Comparator;

public class PatientManager {
// ArrayList usage based on Oracle (n.d.).
    private ArrayList<Patient> patients;
    private String[][] beds;

    public PatientManager() {
        patients = new ArrayList<>();
        beds = new String[4][5];

        // Create bed numbers B01 - B20
        int bedNumber = 1;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                beds[row][col] = "B" + String.format("%02d", bedNumber);
                bedNumber++;
            }
        }
    }

    // Register a new patient
    public boolean registerPatient(Patient patient) {

        if (searchPatient(patient.getPatientId()) != null) {
            return false;
        }

        patients.add(patient);
        return true;
    }

    // Search for a patient using Patient ID
    public Patient searchPatient(String patientId) {

        for (Patient patient : patients) {
            if (patient.getPatientId().equalsIgnoreCase(patientId)) {
                return patient;
            }
        }

        return null;
    }

    // Delete a patient
    public boolean deletePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        patients.remove(patient);
        return true;
    }

    // Update patient details
    public boolean updatePatient(String patientId, String firstName,
                                 String lastName, int age, String gender,
                                 String medicalCondition) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);

        return true;
    }

    // Display all registered patients
    public void displayAllPatients() {

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient patient : patients) {
            System.out.println("----------------------------");
            patient.displayDetails();
        }
    }

    // Display complete ward layout
    public void displayWardLayout() {

        System.out.println("\nWard Layout:");

        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                String bed = beds[row][col];

                if (isBedOccupied(bed)) {
                    System.out.print("[" + bed + ": OCCUPIED] ");
                } else {
                    System.out.print("[" + bed + ": AVAILABLE] ");
                }
            }

            System.out.println();
        }
    }

    // Check whether a bed is occupied
    private boolean isBedOccupied(String bedNumber) {

        for (Patient patient : patients) {

            if (patient instanceof Inpatient) {

                Inpatient inpatient = (Inpatient) patient;

                if (bedNumber.equalsIgnoreCase(inpatient.getBedNumber())) {
                    return true;
                }
            }
        }

        return false;
    }

    // Allocate a bed to an inpatient
    public boolean allocateBed(String patientId, String bedNumber) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        if (!(patient instanceof Inpatient)) {
            return false;
        }

        if (isBedOccupied(bedNumber)) {
            return false;
        }

        if (!bedExists(bedNumber)) {
            return false;
        }

        Inpatient inpatient = (Inpatient) patient;

        inpatient.setWardNumber(1);
        inpatient.setBedNumber(bedNumber.toUpperCase());

        return true;
    }

    // Release a bed
    public boolean releaseBed(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null || !(patient instanceof Inpatient)) {
            return false;
        }

        Inpatient inpatient = (Inpatient) patient;

        if (inpatient.getBedNumber() == null) {
            return false;
        }

        inpatient.setBedNumber(null);
        inpatient.setWardNumber(0);

        return true;
    }

    // Check if bed exists
    private boolean bedExists(String bedNumber) {

        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                if (beds[row][col].equalsIgnoreCase(bedNumber)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Display available beds
    public void displayAvailableBeds() {

        System.out.println("\nAvailable Beds:");

        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                String bed = beds[row][col];

                if (!isBedOccupied(bed)) {
                    System.out.print(bed + " ");
                }
            }
        }

        System.out.println();
    }

    // Display occupied beds
    public void displayOccupiedBeds() {

        System.out.println("\nOccupied Beds:");

        boolean found = false;

        for (Patient patient : patients) {

            if (patient instanceof Inpatient) {

                Inpatient inpatient = (Inpatient) patient;

                if (inpatient.getBedNumber() != null) {

                    System.out.println(
                        inpatient.getBedNumber()
                        + " - "
                        + inpatient.getFirstName()
                        + " "
                        + inpatient.getLastName()
                    );

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No occupied beds.");
        }
    }

    // Count registered patients
    public int getTotalPatients() {
        return patients.size();
    }

    // Count occupied beds
    public int getOccupiedBeds() {

        int count = 0;

        for (Patient patient : patients) {

            if (patient instanceof Inpatient) {

                Inpatient inpatient = (Inpatient) patient;

                if (inpatient.getBedNumber() != null) {
                    count++;
                }
            }
        }

        return count;
    }

    // Calculate ward occupancy percentage
    public double getOccupancyPercentage() {

        return (getOccupiedBeds() / 20.0) * 100;
    }

    // Sort patients by surname
    public void sortBySurname() {

        patients.sort(
            Comparator.comparing(
                Patient::getLastName,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }

    // Sort patients by Patient ID
    public void sortByPatientId() {

        patients.sort(
            Comparator.comparing(
                Patient::getPatientId,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }
}