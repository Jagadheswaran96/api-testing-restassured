package org.restassured.test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import org.restassured.base.BaseTest;
import org.restassured.utility.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest extends BaseTest{

	String token = "caa9cec5c4894b927fda04e3c91d5ba8dcb15ad099270a5b95f48dd917e26465";
	Headers headers = new Headers(
			new Header("Authorization", "Bearer " + token),     // JWT / OAuth2
			new Header("Accept", "application/json"),           // Accept JSON response
			new Header("Content-Type", "application/json"),     // Request is JSON
			new Header("X-Api-Key", "myCustomApiKey123"),       // Custom header
			new Header("User-Agent", "RestAssured-Test")        // Client info
			);

	@Test
	public void deleteRequest() throws Exception {

		ValidatableResponse response = RestAssured
				.given()
				.baseUri("https://httpbin.org/")
				.headers(headers)
				.when()
				.delete("delete") // Use .get(), .post(), .put(), .delete(), etc. as needed")
				.then()
				.log().ifValidationFails()
				.log().ifError()
				.header("Content-Type", equalTo("application/json"))
				.assertThat().header("Content-Type", "application/json")
				.assertThat().time(lessThan(10L), TimeUnit.SECONDS)
				.time(lessThan(10000L))
				.statusCode(200);
		/*
		 * ExtractableResponse<Response> extractedResponse = response.extract(); long
		 * responseTime = extractedResponse.time(); System.out.println("Response Time: "
		 * + responseTime + " ms"); if (responseTime > 10000) {
		 * System.out.println("Warning: Slow API response!"); } // Assert response time
		 * is below 3000 ms Assert.assertTrue(responseTime < 10000,
		 * "Response time is too high!");
		 * 
		 * //Measure Response Time in Different Units long timeInMillis =
		 * extractedResponse.time(); long timeInSeconds =
		 * extractedResponse.timeIn(TimeUnit.SECONDS); long timeInNano =
		 * extractedResponse.timeIn(TimeUnit.NANOSECONDS);
		 * System.out.println("Time in ms: " + timeInMillis);
		 * System.out.println("Time in seconds: " + timeInSeconds);
		 * System.out.println("Time in nanoseconds: " + timeInNano);
		 * 
		 * String contentType = extractedResponse.header("Content-Type");
		 * System.out.println("Content-Type: " + contentType);
		 * System.out.println("Response as String: " + extractedResponse.asString());
		 * System.out.println("Response: " + extractedResponse.asPrettyString());
		 */
	}

	@Test
	public void postRequest() {

		String requestBody = FileUtils.readJsonFile("src/test/resources/request2.json");

		ValidatableResponse response = RestAssured.given()
				//.auth().basic("username", "password")  // Basic Auth
				.baseUri("https://gorest.co.in/")
				.headers(headers)
				.body(requestBody)
				.when()
				.post("public/v2/users") // Use .get(), .post(), .put(), .delete(), etc. as needed")
				.then()
				.statusCode(200)  
				.body("username", equalTo("jagadhes"))     // Verify JSON field
				.body("profile.address.city", equalTo("Chennai"))
				.body("projects.projectName", hasItems("Automation Framework", "API Testing")) // check values exist
				.body("projects.status", everyItem(notNullValue())) // verify no nulls// Verify nested field
				.header("Content-Type", equalTo("application/json"))
				.assertThat().header("Content-Type", "application/json")
				.log().ifValidationFails()
				.log().headers()
				.log().cookies()
				.log().status()
				.log().all(); // Log full response

		ExtractableResponse<Response> extractedResponse = response.extract();
		long responseTime = extractedResponse.time();
		System.out.println("Response Time: " + responseTime + " ms");
		if (responseTime > 5000) {
			System.out.println("Warning: Slow API response!");
		}
		//Assert response time is below 3000 ms
		Assert.assertFalse(responseTime > 2000, "Response time is too high!");

		String contentType = extractedResponse.header("Content-Type");
		System.out.println("Content-Type: " + contentType);

		JsonPath jsonPath = new JsonPath(extractedResponse.asString());
		//Extract all project names into a List
		List<String> projectNames = jsonPath.getList("projects.projectName");
		System.out.println("All Projects: " + projectNames);
		for (String project : projectNames) {
			System.out.println("Project: " + project);
			if (project.isEmpty()) {
				throw new AssertionError("Project name is empty!");
			}
		}

		//Get array size
		int projectCount = jsonPath.getList("projects").size();
		System.out.println("Total Projects: " + projectCount);
		// Verify expected size
		if (projectCount != 3) {
			//throw new AssertionError("Expected 3 projects but found " + projectCount);
		}

		List<String> activeProjects = jsonPath.getList("projects.findAll { it.status == 'active' }.projectName");
		System.out.println("Active Projects: " + activeProjects);

		List<String> projectName = extractedResponse.jsonPath().getList("projects.projectName");
		System.out.println("Projects: " + projectName);
		if (!projectNames.contains("Automation Framework")) {
			throw new AssertionError("Expected project not found!");
		}

		String username = extractedResponse.jsonPath().getString("username");
		String city = extractedResponse.jsonPath().getString("profile.address.city");
		System.out.println("Username: " + username);
		System.out.println("City: " + city);
		// Manual validation
		if (!"jagadhes".equals(username)) {
			throw new AssertionError("Username mismatch!");
		}
		if (!"Chennai".equals(city)) {
			throw new AssertionError("City mismatch!");
		}

		System.out.println("Response as String: " + extractedResponse.asString());
		System.out.println("Response: " + extractedResponse.asPrettyString());
	}
}

