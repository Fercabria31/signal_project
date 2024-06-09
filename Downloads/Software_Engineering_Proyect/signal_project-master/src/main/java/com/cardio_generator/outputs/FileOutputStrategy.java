package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a public class that output patient data to files using a specific strategy by implementing OutputStrategy.
 * It creates files in a specified base directory and writes the patient data to those files.
 * 
 * @author Fernando Cabria Serena and Esteban Naranjo Amortegui
 */
public class FileOutputStrategy implements OutputStrategy {

    // Variable name starts with a lowercase letter and uses camelCase
    private String baseDirectory;

    // This variable should declared private
    private final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    /**
     * Makes a new FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory The base directory where files will be created.
     */
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Outputs patient data to files.
     *
     * @param patientId The ID of the patient with the data.
     * @param timestamp The timestamp indicating when the data was generated.
     * @param label The label of data which is being outputted.
     * @param data The actual data which will be outputted.
     */

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            // Variable name uses camelCase, and "baseDirectory" should start with lowercase
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            // Error message is printed to System.err
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the filePath variable
        // Variable names use camelCase and "filePath" should start with lowercase
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                // Variable name uses camelCase
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            // Add space between variables for readability
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
