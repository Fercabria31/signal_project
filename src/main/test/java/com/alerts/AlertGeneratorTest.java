package com.alerts;

import static org.junit.jupiter.api.Assertions.*;

import com.data_management.DataStorage;
import org.junit.jupiter.api.Test;

class AlertGeneratorTest {

  @Test
  void testBloodPressureAlerts() {
    DataStorage storage = DataStorage.getInstance();
    storage.addPatientData(2024, 55, "DiastolicPressure", System.currentTimeMillis());
    storage.addPatientData(2024, 190, "SystolicPressure", System.currentTimeMillis());

    AlertGenerator alerts = new AlertGenerator(storage);
    alerts.evaluateData(storage.getAllPatients().get(0));
    alerts.evaluateData(storage.getAllPatients().get(1));

    assertEquals(2, storage.getRecords(2024, 0, System.currentTimeMillis() + 1).size());
    assertEquals(0, storage.getRecords(2025, 0, System.currentTimeMillis() + 1).size());
  }

}