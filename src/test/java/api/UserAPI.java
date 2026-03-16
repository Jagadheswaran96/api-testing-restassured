package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class UserAPI {
	
	public static Response getUser(int userId) {

        return given()
                .header("Content-Type","application/json")
                .when()
                .get("/users/" + userId)
                .then()
                .extract()
                .response();
    }

}
