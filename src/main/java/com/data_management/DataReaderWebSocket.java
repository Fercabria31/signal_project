package com.data_management;

public interface DataReaderWebSocket {
    // New DataReader for WebSocketDataReader
    void connect(String uri);
    void disconnect();
    void onMessage(String message);
}