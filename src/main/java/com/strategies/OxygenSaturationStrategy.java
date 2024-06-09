package com.strategies;

import com.data_management.PatientRecord;

/**
 * Concrete strategy for checking oxygen saturation alerts.
 */
public class OxygenSaturationStrategy implements AlertStrategy {
    private static final double MIN_OXYGEN_LEVEL = 90.0;

    @Override
    public boolean checkAlert(PatientRecord record) {
        return record.getRecordType().equals("OxygenSaturation") && record.getMeasurementValue() < MIN_OXYGEN_LEVEL;
    }
}