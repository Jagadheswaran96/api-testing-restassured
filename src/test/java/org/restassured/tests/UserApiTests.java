package org.restassured.tests;

import com.restassured.base.ApiClient;
import com.restassured.models.User;

import api.UserApi;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import validators.*;
import io.qameta.allure.*;

@Epic("User API")
@Feature("User Management")
public class UserApiTests {
	private ApiClient client;

	@BeforeClass
	public void setup() {
		client = new ApiClient();
	}

	@Test(description = "Verify user creation")
	@Story("Create User")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateUser() throws Exception {

		/*
		 * int userId = 2;
		 * 
		 * Response response = UserApi.getUser(userId);
		 * 
		 * ResponseValidator.validateStatus(response,200);
		 * 
		 * ResponseValidator.validateHeaders(response);
		 * 
		 * ResponseValidator.validateField(response, "name", "John");
		 * 
		 * SchemaValidator.validateSchema(response,"schemas/user-schema.json");
		 * 
		 * BusinessValidator.validateUserId(response,userId);
		 * 
		 * DatabaseValidator.validateUserEmail(response,userId);
		 */

		User newUser = new User("Jagadheswaran", 101);

		Response response = client.post("/users", newUser);

		// Validate status code
		Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch");

		// Validate response body
		Assert.assertEquals(response.jsonPath().getString("name"), "Jagadheswaran");
		Assert.assertEquals(response.jsonPath().getInt("id"), 101);
	}

	@Test(description = "Verify user retrieval")
	@Story("Get User")
    @Severity(SeverityLevel.NORMAL)
	public void testGetUser() {
		Response response = client.get("/users/2");

		// Validate status code
		Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

		// Validate response body
		Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
		Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
	}

	@Test(description = "Verify user update")
	@Story("Update User")
    @Severity(SeverityLevel.NORMAL)
	public void testUpdateUser() {
		User updatedUser = new User("UpdatedName", 101);

		Response response = client.put("/users/101", updatedUser);

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "UpdatedName");
	}

	@Test(description = "Verify user deletion")
	@Story("Delete User")
    @Severity(SeverityLevel.MINOR)
	public void testDeleteUser() {
		Response response = client.delete("/users/101");

		Assert.assertEquals(response.getStatusCode(), 204);
	}
}
