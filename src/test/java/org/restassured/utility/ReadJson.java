package org.restassured.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ReadJson {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Read JSON file into a Java Map
        File file = new File("src/test/resources/request.json");
        MyData data = objectMapper.readValue(file, MyData.class);
        
        System.out.println("Username: " + data.getUsername());
    }
}

