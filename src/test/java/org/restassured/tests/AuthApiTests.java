package org.restassured.tests;

import com.restassured.base.ApiClient;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Auth API")
@Feature("Auth Management")
public class AuthApiTests {
    private ApiClient client;

    @BeforeClass
    public void setup() {
        client = new ApiClient();
    }

    @Test(description = "Verify login with valid credentials")
    public void testLoginValid() {
        String payload = "{ \"username\": \"admin\", \"password\": \"password123\" }";
        Response response = client.post("/login", payload);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("token"));
    }

    @Test(description = "Verify login with invalid credentials")
    public void testLoginInvalid() {
        String payload = "{ \"username\": \"admin\", \"password\": \"wrongpass\" }";
        Response response = client.post("/login", payload);

        Assert.assertEquals(response.getStatusCode(), 401);
    }
}
