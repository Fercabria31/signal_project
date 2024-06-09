package com.strategies;

import com.data_management.PatientRecord;

/**
 * Strategy interface for checking alerts based on health metrics.
 */
public interface AlertStrategy {
    boolean checkAlert(PatientRecord record);
}
