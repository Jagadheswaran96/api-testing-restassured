package org.restassured.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //Ignores unknown fields at the class level
//@JsonIgnoreProperties({"userId", "user_name"}) //Ignores multiple fields at the class level
public class MyData {
	
	@JsonProperty("user_name") //Maps a JSON field to a Java field (useful when names differ)
	private String username;
	@JsonIgnore //Ignores a field during serialization/deserialization
	private String password;
	
	//Default constructor
	public MyData() {
		
    }
	
	//Argument constructor to send data to this class fields
	public MyData(String username, String password) {
		this.username = username;
		this.password = password;
		
    }

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

