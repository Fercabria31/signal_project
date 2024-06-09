package com.alerts;

/**
 * Factory for creating ECG alerts.
 */
public class ECGAlertFactory extends AlertFactory {

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new ECGAlert(patientId, condition, timestamp);
    }
}