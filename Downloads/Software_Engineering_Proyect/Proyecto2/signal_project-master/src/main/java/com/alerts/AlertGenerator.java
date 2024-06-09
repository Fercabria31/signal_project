package com.alerts;

import java.util.List;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private boolean isAlertTriggered;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.isAlertTriggered = false;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        evaluateBloodPressureData(patient);
        evaluateBloodSaturationData(patient);
        evaluateHypotensiveHypoxemiaAlert(patient);
        evaluateECGData(patient);
        evaluateTriggeredAlert(patient);
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        System.out.println("Alert triggered: " + alert);
        this.isAlertTriggered = true;
    }
    private void evaluateBloodPressureData(Patient patient){
        // Trend alert
        List<PatientRecord> records = patient.getRecords(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000, System.currentTimeMillis());
        if (records.size() >= 3) {
            double prevSystolic = records.get(0).getMeasurementValue();
            double prevDiastolic = records.get(0).getMeasurementValue();
            boolean increasingTrend = true;
            boolean decreasingTrend = true;
            for (int i = 1; i < records.size(); i++) {
                double currentSystolic = records.get(i).getMeasurementValue();
                double currentDiastolic = records.get(i).getMeasurementValue();
                if (prevSystolic - currentSystolic < -10 || prevDiastolic - currentDiastolic < -10) {
                    decreasingTrend = false;
                }
                if (prevSystolic - currentSystolic > 10 || prevDiastolic - currentDiastolic > 10) {
                    increasingTrend = false;
                }
                prevSystolic = currentSystolic;
                prevDiastolic = currentDiastolic;
            }
            if (increasingTrend || decreasingTrend) {
                triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Blood Pressure Trend Alert", System.currentTimeMillis()));
            }
        }

        // Critical Threshold Alert
        for (PatientRecord record : records) {
            double systolic = record.getMeasurementValue();
            double diastolic = record.getMeasurementValue();
            if (systolic > 180 || systolic < 90 || diastolic > 120 || diastolic < 60) {
                triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Critical Blood Pressure Threshold Alert", System.currentTimeMillis()));
                break;
            }
        }
    }
    private void evaluateBloodSaturationData(Patient patient) {
        // Low Saturation Alert
        List<PatientRecord> saturationRecords = patient.getRecords(System.currentTimeMillis() - 24 * 60 * 60 * 1000, System.currentTimeMillis());
        for (PatientRecord record : saturationRecords) {
            if (record.getRecordType().equals("Blood Saturation") && record.getMeasurementValue() < 92) {
                triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Low Saturation Alert", System.currentTimeMillis()));
                break;
            }
        }

        // Rapid Drop Alert
        long interval = 10 * 60 * 1000; // 10 minutes
        for (int i = 0; i < saturationRecords.size() - 1; i++) {
            PatientRecord currentRecord = saturationRecords.get(i);
            PatientRecord nextRecord = saturationRecords.get(i + 1);
            if (nextRecord.getTimestamp() - currentRecord.getTimestamp() <= interval && currentRecord.getMeasurementValue() - nextRecord.getMeasurementValue() >= 5) {
                triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Rapid Saturation Drop Alert", System.currentTimeMillis()));
                break;
            }
        }
    }
    private void evaluateHypotensiveHypoxemiaAlert(Patient patient) {
        // Get the latest blood pressure and saturation records
        List<PatientRecord> bpRecords = patient.getRecords(System.currentTimeMillis() - 24 * 60 * 60 * 1000, System.currentTimeMillis());
        List<PatientRecord> saturationRecords = patient.getRecords(System.currentTimeMillis() - 24 * 60 * 60 * 1000, System.currentTimeMillis());

        // Check if both conditions are met
        boolean lowBloodPressure = false;
        boolean lowSaturation = false;

        // Check for low blood pressure
        for (PatientRecord record : bpRecords) {
            if (record.getRecordType().equals("Systolic Blood Pressure") && record.getMeasurementValue() < 90) {
                lowBloodPressure = true;
                break;
            }
        }

        // Check for low saturation
        for (PatientRecord record : saturationRecords) {
            if (record.getRecordType().equals("Blood Saturation") && record.getMeasurementValue() < 92) {
                lowSaturation = true;
                break;
            }
        }

        // Trigger alert if both conditions are met
        if (lowBloodPressure && lowSaturation) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Hypotensive Hypoxemia Alert", System.currentTimeMillis()));
        }

    }
    private void evaluateECGData(Patient patient) {
        double previousHeartRate = -1;
        boolean irregularBeatDetected = false;
        int irregularHeartBeatThreshold = 30;
        List<PatientRecord> ecgRecords = patient.getRecords(System.currentTimeMillis() - 24 * 60 * 60 * 1000, System.currentTimeMillis());
        // Abnormal heart rate alert
        for (PatientRecord record : ecgRecords) {
            if (record.getRecordType().equals("Heart Rate")) {
                double heartRate = record.getMeasurementValue();
                if (heartRate < 50 || heartRate > 100) {
                    triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Abnormal Heart Rate Alert", System.currentTimeMillis()));
                    break;
                }
            }
        }
        //Irregular heart beat alert
        for (PatientRecord record : ecgRecords) {
            if (record.getRecordType().equals("Heart Rate")) {
                double heartRate = record.getMeasurementValue();
                if (previousHeartRate != -1) {
                    double difference = Math.abs(heartRate - previousHeartRate);
                    if (difference > irregularHeartBeatThreshold) { // Define your threshold for irregular beats
                        irregularBeatDetected = true;
                        break;
                    }
                }

                previousHeartRate = heartRate;
            }
        }
    }
    private void evaluateTriggeredAlert(Patient patient) {
        if (isAlertTriggered()) {
            triggerAlert(new Alert(Integer.toString(patient.getPatientId()), "Triggered Alert", System.currentTimeMillis()));
            resetAlert();
        }
    }

    public void resetAlert() {
        this.isAlertTriggered = false;
    }

    public boolean isAlertTriggered() {
        return this.isAlertTriggered;
    }
}
