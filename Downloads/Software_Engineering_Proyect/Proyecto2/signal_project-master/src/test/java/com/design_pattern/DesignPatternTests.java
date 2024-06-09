package com.design_pattern;

import com.alerts.*;
import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class DesignPatternTests {
    @Test
    public void testBloodPressureAlertFactory() {
        AlertFactory factory = new BloodPressureAlertFactory();
        Alert alert = factory.createAlert("123", "High", System.currentTimeMillis());
        assertEquals("BloodPressureAlert", alert.getClass().getSimpleName());
    }

    @Test
    public void testBloodOxygenAlertFactory() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        Alert alert = factory.createAlert("456", "Low", System.currentTimeMillis());
        assertEquals("BloodOxygenAlert", alert.getClass().getSimpleName());
    }

    @Test
    public void testECGAlertFactory() {
        AlertFactory factory = new ECGAlertFactory();
        Alert alert = factory.createAlert("789", "Irregular", System.currentTimeMillis());
        assertEquals("ECGAlert", alert.getClass().getSimpleName());
    }

    @Test
    public void testDataStorageSingleton() {
        DataStorage storage1 = DataStorage.getInstance();
        DataStorage storage2 = DataStorage.getInstance();
        assertSame(storage1, storage2);
    }

    @Test
    public void testHealthDataSimulatorSingleton() {
        HealthDataSimulator simulator1 = HealthDataSimulator.getInstance();
        HealthDataSimulator simulator2 = HealthDataSimulator.getInstance();
        assertSame(simulator1, simulator2);
    }

}
