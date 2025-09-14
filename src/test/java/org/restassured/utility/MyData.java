package org.restassured.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyData {
	
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() { 
    	return username; 
    	}
    public void setUsername(String username) { 
    	this.username = username; 
    	}

    public String getPassword() { 
    	return password; 
    	}
    public void setPassword(String password) {
    	this.password = password; 
    	}
}

