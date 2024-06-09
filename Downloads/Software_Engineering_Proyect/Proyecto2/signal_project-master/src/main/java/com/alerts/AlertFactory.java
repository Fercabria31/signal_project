package com.alerts;

/**
 * Base factory class for creating alerts.
 */
public abstract class AlertFactory {
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
