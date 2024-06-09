package com.strategies;

import com.data_management.PatientRecord;

/**
 * Concrete strategy for checking heart rate alerts.
 */
public class HeartRateStrategy implements AlertStrategy {
    private static final int MAX_HEART_RATE = 100;

    @Override
    public boolean checkAlert(PatientRecord record) {
        return record.getRecordType().equals("HeartRate") && record.getMeasurementValue() > MAX_HEART_RATE;
    }
}
