package com.data_management;

import java.io.IOException;

public interface DataReader {
    
    void readData(DataStorage dataStorage) throws IOException;

}
