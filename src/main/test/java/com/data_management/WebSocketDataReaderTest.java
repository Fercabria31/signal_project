package com.data_management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.websocket.Session;
import java.io.IOException;

class WebSocketDataReaderTest {

    private WebSocketDataReader webSocketDataReader;
    private DataStorage mockDataStorage;
    private Session mockSession;

    @BeforeEach
    void setUp() {
        mockDataStorage = mock(DataStorage.class);
        webSocketDataReader = new WebSocketDataReader(mockDataStorage);
        mockSession = mock(Session.class);
    }

    @Test
    void testConnectToWebSocket() throws IOException {
        String url = "ws://localhost:8080/websocket";
        webSocketDataReader.connectToWebSocket(url);

        // Assuming connectToWebSocket will set userSession, which would be used in the real implementation
        assertNotNull(webSocketDataReader.getUserSession());
    }

    @Test
    void testOnMessage() {
        String message = "{\"patientId\": 1, \"timestamp\": 1714376789050, \"label\": \"WhiteBloodCells\", \"data\": 100.0}";

        webSocketDataReader.onMessage(message);

        ArgumentCaptor<Integer> patientIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Double> measurementValueCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> recordTypeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> timestampCaptor = ArgumentCaptor.forClass(Long.class);

        verify(mockDataStorage).updatePatientData(any(WebSocketDataReader.PatientData.class));

        // Add your assertions here based on how you structure PatientData class
    }

    @Test
    void testDisconnect() throws IOException {
        webSocketDataReader.onOpen(mockSession);
        assertNotNull(webSocketDataReader.getUserSession());

        webSocketDataReader.disconnect();
        verify(mockSession).close();
    }

    @Test
    void testOnOpen() {
        webSocketDataReader.onOpen(mockSession);
        assertNotNull(webSocketDataReader.getUserSession());
    }

    @Test
    void testOnClose() {
        webSocketDataReader.onOpen(mockSession);
        assertNotNull(webSocketDataReader.getUserSession());

        webSocketDataReader.onClose(mockSession, null);
        assertNull(webSocketDataReader.getUserSession());
    }
}
