package com.data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class OutputDataReaderTest {
    private OutputDataReader outputDataReader;

    @BeforeEach
    void setUp() {
        // Initialize the OutputDataReader object
        outputDataReader = new OutputDataReader();
    }

    @Test
    void testReadPatientData() {
        // Define test data
        String filePath = "test_data/output_data.txt"; // Path to test data file
        int expectedNumberOfPatients = 3; // Expected number of patients in the test data

        // Read patient data from the test file
        List<Patient> patients = outputDataReader.readData(filePath);

        // Assert that the correct number of patients is read
        assertEquals(expectedNumberOfPatients, patients.size());

        // Additional assertions can be added to verify the correctness of the read data
        // For example, you can check specific patient records or attributes
    }

    @Test
    void testReadPatientDataWithEmptyFile() {
        // Define test data
        String filePath = "test_data/empty_file.txt"; // Path to an empty test data file
        int expectedNumberOfPatients = 0; // Expected number of patients when the file is empty

        // Read patient data from the empty file
        List<Patient> patients = outputDataReader.readData("C:\\Users\\ferca\\Downloads\\Prueba.txt");

        // Assert that no patients are read from the empty file
        assertEquals(expectedNumberOfPatients, patients.size());
    }

    @Test
    void testReadPatientDataWithInvalidFile() {
        // Define test data
        String filePath = "test_data/invalid_file.txt"; // Path to a non-existent or invalid test data file

        // Attempt to read patient data from the invalid file
        List<Patient> patients = outputDataReader.readData(filePath);

        // Assert that no patients are read from the invalid file
        assertTrue(patients.isEmpty());
    }
}
