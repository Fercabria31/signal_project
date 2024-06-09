package com.strategies;

import com.data_management.PatientRecord;

/**
 * Concrete strategy for checking blood pressure alerts.
 */
public class BloodPressureStrategy implements AlertStrategy {
    private static final double HIGH_THRESHOLD = 140.0;

    @Override
    public boolean checkAlert(PatientRecord record) {
        return record.getRecordType().equals("BloodPressure") && record.getMeasurementValue() > HIGH_THRESHOLD;
    }
}
