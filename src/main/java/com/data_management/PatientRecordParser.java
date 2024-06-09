package com.data_management;

public class PatientRecordParser {
    // ParserClass used in WebSocketDataReader for PatientRecord
    public static PatientRecord parseLine(String line) {
            int patientId = getPatientId(line);
            long timestamp = getTimestamp(line);
            String label = getLabel(line);
            Double data = getData(line, label);

            return new PatientRecord(patientId, data, label, timestamp);
    }

    private static int getPatientId(String line){
        String patientIdPrefix = "Patient ID";
        return Integer.parseInt(getField(line, patientIdPrefix));
    }
    private static long getTimestamp(String line){
        String timestampPrefix = "Timestamp";
        return Long.parseLong(getField(line, timestampPrefix));
    }
    private static String getLabel(String line){
        String labelPrefix = "Label";
        return getField(line, labelPrefix);
    }
    private static Double getData(String line, String label){
        String labelPrefix = "Data";
        String fieldValue = getField(line, labelPrefix).replace("%", "");
            return Double.parseDouble(fieldValue);
    }

    private static String getField(String line, String fieldName){
        String fieldPrefix = fieldName + ": ";
        int startOfFieldValue = line.indexOf(fieldPrefix) + fieldPrefix.length();
        int endOfFieldValue = line.indexOf(",", startOfFieldValue);
        if(endOfFieldValue == -1)
        {
            endOfFieldValue = line.length();
        }
        return line.substring(startOfFieldValue, endOfFieldValue);
    }

}