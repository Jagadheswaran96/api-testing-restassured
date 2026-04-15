package org.restassured.utility;

import static io.restassured.RestAssured.*;

public class RetrieveToken {
	
	public static String getToken() {

        return given()
                .contentType("application/json")
                .body("{\"username\":\"admin\",\"password\":\"admin123\"}")
        .when()
                .post("/auth/login")
        .then()
                .extract()
                .path("token");
    }

}
