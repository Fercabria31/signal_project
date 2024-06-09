package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * This class generates alert data for patients.
 * Based on some conditions it triggers an alert using a OutputStrategy.
 *
 * @author Fernando Cabria Serena and Esteban Naranjo Amortegui
 */

public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    // Variable name starts with lowercase
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * Constructs a new AlertGenerator with the specified number of patients.
     *
     * @param patientCount The number of patients for which alert data will be generated.
     */

    public AlertGenerator(int patientCount) {
        //Variable with lowercase
        alertStates = new boolean[patientCount + 1];
    }
    /**
     * Generates alert data for the specified patient and outputs it using a specified OutputStrategy.
     *
     * @param patientId The ID of the patient for which alert data is generated.
     * @param outputStrategy The output strategy used to output the generated alert data.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Variable starts with lowercase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
