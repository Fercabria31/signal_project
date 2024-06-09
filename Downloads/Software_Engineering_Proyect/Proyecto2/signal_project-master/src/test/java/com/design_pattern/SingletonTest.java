package com.design_pattern;

import static org.junit.Assert.*;
import org.junit.Test;
import com.data_management.*;
import com.cardio_generator.*;

public class SingletonTest {

    @Test
    public void testDataStorageSingleton() {
        DataStorage storage1 = DataStorage.getInstance();
        DataStorage storage2 = DataStorage.getInstance();
        assertSame(storage1, storage2); // Ensure the same instance is returned
    }

    @Test
    public void testHealthDataSimulatorSingleton() {
        HealthDataSimulator simulator1 = HealthDataSimulator.getInstance();
        HealthDataSimulator simulator2 = HealthDataSimulator.getInstance();
        assertSame(simulator1, simulator2); // Ensure the same instance is returned
    }
}
