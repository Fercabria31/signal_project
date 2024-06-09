package com.data_management;

import org.java_websocket.client.WebSocketClient;


public class WebSocketDataReader implements DataReaderWebSocket {

    private String uri;
    private DataStorage dataStorage;
    public WebSocketClient client;

  public void readData(DataStorage dataStorage) { 
    this.dataStorage = dataStorage;
}
@Override
public void connect(String uri) {
    try {
        this.uri = uri;
        client.connectBlocking();
    } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException("Connection interrupted");
    }
    throw new UnsupportedOperationException("Unimplemented method 'connect'");
}

@Override
public void disconnect() {
    if (client != null) {
        try {
            client.close();
            client = null;
        } catch (Exception e) {
            System.out.println("Failed to disconnect");
            e.printStackTrace();
        }
    }
    throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
}

@Override
public void onMessage(String message) {
    PatientRecord patientRecord = PatientRecordParser.parseLine(message);
    dataStorage.addPatientData(patientRecord.getPatientId(), patientRecord.getMeasurementValue(),
    patientRecord.getRecordType(), patientRecord.getTimestamp());

    throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
}

}
