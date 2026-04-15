package org.restassured.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ReadJson {
	
    public static void main(String[] args) throws IOException {
    	
    	// Read JSON file into a Java Map
        File file = new File("src/test/resources/schemas/request.json");
    	
        ObjectMapper mapper = new ObjectMapper();
        
        //Deserialize JSON content from given file into given Java type
        MyData data = mapper.readValue(file, MyData.class);        
        System.out.println("Username: " + data.getUsername());
        System.out.println("JSON: " + data.toString());
        
        //Tree Node Model (Dynamic JSON Handling) when POJO not used
        JsonNode node = mapper.readTree(file);
        System.out.println("Email: " + node.get("email").asText());
        
        //Serialization (Java → JSON) - Converts Java objects into JSON strings
        String json = mapper.writeValueAsString(new MyData("Jagadheswaran", "pwd"));
        System.out.println(json);
        
        mapper.writeValue(new File("src/test/resources/responses/response.json"), json);
    }
}