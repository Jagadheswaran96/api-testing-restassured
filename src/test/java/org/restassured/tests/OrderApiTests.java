package org.restassured.tests;

import com.restassured.base.ApiClient;
import com.restassured.models.Order;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Order API")
@Feature("Order Management")
public class OrderApiTests {
    private ApiClient client;

    @BeforeClass
    public void setup() {
        client = new ApiClient();
    }

    @Test(description = "Verify order creation")
    public void testCreateOrder() {
        Order newOrder = new Order(5001, "Laptop", 2);
        Response response = client.post("/orders", newOrder);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("productName"), "Laptop");
    }

    @Test(description = "Verify order retrieval")
    public void testGetOrder() {
        Response response = client.get("/orders/5001");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("orderId"), 5001);
    }
}
