package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputDataReader implements DataReader {

    private String outputDirectory;

    public OutputDataReader(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
public void readData(DataStorage dataStorage) throws IOException {
    Path directoryPath = Paths.get(outputDirectory);

    if (!Files.exists(directoryPath)) {
        throw new IOException("Output directory does not exist: " + outputDirectory);
    }

    // Iterate through files in the directory
    Files.list(directoryPath)
            .filter(Files::isRegularFile)
            .forEach(filePath -> {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                    // Read data from each file and pass it to DataStorage
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Split the line into components
                        String[] parts = line.split(",");
                        if (parts.length == 4) {
                            // Extract data from parts
                            int patientId = Integer.parseInt(parts[0]);
                            double measurementValue = Double.parseDouble(parts[1]);
                            String recordType = parts[2];
                            long timestamp = Long.parseLong(parts[3]);
                            // Add data to DataStorage
                            dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                        } else {
                            System.err.println("Invalid data format: " + line);
                            // Handle invalid data format
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace(); // Handle or log the exception as needed
                }
            });
}

}

