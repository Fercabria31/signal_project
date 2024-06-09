package com.design_pattern;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.alerts.*;

public class AlertFactoryTest {

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
}
