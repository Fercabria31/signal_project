package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

// Class name starts with an uppercase letter and uses camelCase
public class FileOutputStrategy implements OutputStrategy {

    // Variable name starts with a lowercase letter and uses camelCase
    private String baseDirectory;

    // This variable should declared private
    private final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    // Constructor parameter name uses uppercase and camelCase
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

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
