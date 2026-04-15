package org.restassured.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.response.Response;

public class FileUtils {

    public static String readJsonFile(String filePath) {
        try {
        	// Read JSON request from file
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
    
    public static Path writeJsonResponse(Response response) {
        try {
        	// Write raw response to file
        	return Files.write(Paths.get("src/test/resources/responses/response.json"),
        	            response.getBody().asPrettyString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON file: " + response, e);
        }
    }
}