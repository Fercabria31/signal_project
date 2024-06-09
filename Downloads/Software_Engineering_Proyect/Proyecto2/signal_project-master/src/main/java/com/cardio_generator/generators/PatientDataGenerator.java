package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * This class represents an interface that other classes can use in order to generate patient data.
 * Classes implementing this interface are responsible for generating simulated patient data
 * and outputs it using a strategy specified in OutputStrategy variable.
 *  
 * @author Fernando Cabria Serena and Esteban Naranjo Amortegui
 */
public interface PatientDataGenerator {

    /**
     * Generates patient data for the specified patient ID and outputs it using the specified OutputStrategy.
     * @param patientId The ID of the patient for which data is generated.
     * @param outputStrategy The output strategy used to output the generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
