/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalsystem;

// Junit 5 testing based on JUunit Team (n.d.).
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    // Test 1: Register a patient
    @Test
    public void testRegisterPatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        assertTrue(manager.registerPatient(patient));
        assertNotNull(manager.searchPatient("P001"));
    }

    // Test 2: Search for a patient
    @Test
    public void testSearchPatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P002",
                "Mary",
                "Jones",
                25,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        Patient found = manager.searchPatient("P002");

        assertNotNull(found);
        assertEquals("Mary", found.getFirstName());
        assertEquals("Jones", found.getLastName());
    }

    // Test 3: Update patient details
    @Test
    public void testUpdatePatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P003",
                "Peter",
                "Brown",
                40,
                "Male",
                "Headache",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        boolean result = manager.updatePatient(
                "P003",
                "Peter",
                "Williams",
                41,
                "Male",
                "Migraine"
        );

        assertTrue(result);

        Patient updated = manager.searchPatient("P003");

        assertEquals("Williams", updated.getLastName());
        assertEquals(41, updated.getAge());
        assertEquals("Migraine", updated.getMedicalCondition());
    }

    // Test 4: Delete a patient
    @Test
    public void testDeletePatient() {

        PatientManager manager = new PatientManager();

        Patient patient = new Patient(
                "P004",
                "Sarah",
                "Davis",
                35,
                "Female",
                "Diabetes",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        assertTrue(manager.deletePatient("P004"));
        assertNull(manager.searchPatient("P004"));
    }

    // Test 5: Allocate a bed
    @Test
    public void testAllocateBed() {

        PatientManager manager = new PatientManager();

        Inpatient patient = new Inpatient(
                "P005",
                "James",
                "Miller",
                50,
                "Male",
                "Pneumonia",
                0,
                null
        );

        manager.registerPatient(patient);

        assertTrue(manager.allocateBed("P005", "B01"));

        assertEquals("B01", patient.getBedNumber());
        assertEquals(1, patient.getWardNumber());
    }

    // Test 6: Release a bed
    @Test
    public void testReleaseBed() {

        PatientManager manager = new PatientManager();

        Inpatient patient = new Inpatient(
                "P006",
                "Linda",
                "Wilson",
                60,
                "Female",
                "Heart condition",
                0,
                null
        );

        manager.registerPatient(patient);

        manager.allocateBed("P006", "B02");

        assertTrue(manager.releaseBed("P006"));

        assertNull(patient.getBedNumber());
        assertEquals(0, patient.getWardNumber());
    }

    // Test 7: Prevent duplicate Patient IDs
    @Test
    public void testPreventDuplicatePatientIds() {

        PatientManager manager = new PatientManager();

        Patient patient1 = new Patient(
                "P007",
                "Tom",
                "Taylor",
                28,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P007",
                "Anna",
                "Taylor",
                32,
                "Female",
                "Flu",
                PatientCategory.EMERGENCY
        );

        assertTrue(manager.registerPatient(patient1));
        assertFalse(manager.registerPatient(patient2));

        assertEquals(1, manager.getTotalPatients());
    }

    // Test 8: Prevent allocating an occupied bed
    @Test
    public void testPreventAllocatingOccupiedBed() {

        PatientManager manager = new PatientManager();

        Inpatient patient1 = new Inpatient(
                "P008",
                "David",
                "Moore",
                45,
                "Male",
                "Infection",
                0,
                null
        );

        Inpatient patient2 = new Inpatient(
                "P009",
                "Susan",
                "Moore",
                38,
                "Female",
                "Asthma",
                0,
                null
        );

        manager.registerPatient(patient1);
        manager.registerPatient(patient2);

        assertTrue(manager.allocateBed("P008", "B03"));

        assertFalse(manager.allocateBed("P009", "B03"));
    }

    // Test 9: Prevent bed allocation when all beds are occupied
    @Test
    public void testPreventAllocationWhenAllBedsOccupied() {

        PatientManager manager = new PatientManager();

        // Fill all 20 beds
        for (int i = 1; i <= 20; i++) {

            String patientId = "P" + String.format("%03d", i);

            Inpatient patient = new Inpatient(
                    patientId,
                    "Patient",
                    "Number" + i,
                    30,
                    "Male",
                    "Condition",
                    0,
                    null
            );

            manager.registerPatient(patient);

            String bedNumber = "B" + String.format("%02d", i);

            assertTrue(manager.allocateBed(patientId, bedNumber));
        }

        // Try to add another patient
        Inpatient extraPatient = new Inpatient(
                "P021",
                "Extra",
                "Patient",
                25,
                "Female",
                "Condition",
                0,
                null
        );

        manager.registerPatient(extraPatient);

        // No bed should be available
        assertFalse(manager.allocateBed("P021", "B01"));

        assertEquals(20, manager.getOccupiedBeds());
    }

    // Test 10: Prevent non-inpatient from receiving a bed
    @Test
    public void testOnlyInpatientCanReceiveBed() {

        PatientManager manager = new PatientManager();

        Patient outpatient = new Patient(
                "P022",
                "Robert",
                "King",
                29,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(outpatient);

        assertFalse(manager.allocateBed("P022", "B01"));
    }

    // Test 11: Sort patients by surname
    @Test
    public void testSortBySurname() {

        PatientManager manager = new PatientManager();

        Patient patient1 = new Patient(
                "P023",
                "John",
                "Zulu",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P024",
                "Mary",
                "Adams",
                25,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient1);
        manager.registerPatient(patient2);

        manager.sortBySurname();

        assertEquals("Adams",
                manager.searchPatient("P024").getLastName());
    }

    // Test 12: Sort patients by Patient ID
    @Test
    public void testSortByPatientId() {

        PatientManager manager = new PatientManager();

        Patient patient1 = new Patient(
                "P100",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P050",
                "Mary",
                "Jones",
                25,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient1);
        manager.registerPatient(patient2);

        manager.sortByPatientId();

        assertNotNull(manager.searchPatient("P050"));
        assertNotNull(manager.searchPatient("P100"));
    }

    // Test 13: Occupancy percentage
    @Test
    public void testOccupancyPercentage() {

        PatientManager manager = new PatientManager();

        Inpatient patient = new Inpatient(
                "P025",
                "Michael",
                "Smith",
                50,
                "Male",
                "Injury",
                0,
                null
        );

        manager.registerPatient(patient);
        manager.allocateBed("P025", "B01");

        assertEquals(5.0, manager.getOccupancyPercentage());
    }
}