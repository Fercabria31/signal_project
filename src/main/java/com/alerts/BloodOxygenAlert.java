package com.alerts;

/**
 * Class representing a blood oxygen alert.
 */
public class BloodOxygenAlert extends Alert {
    public BloodOxygenAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }

    @Override
    public String toString() {
        return "BloodOxygenAlert{" +
                "patientId='" + getPatientId() + '\'' +
                ", condition='" + getCondition() + '\'' +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}