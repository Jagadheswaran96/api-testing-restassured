package com.restassured.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class ApiClient {
    private RequestSpecification request;

    public ApiClient() {
        this.request = RestAssured.given().contentType("application/json");
    }

    public Response get(String endpoint) {
        return request.when().get(endpoint).then().extract().response();
    }

    public Response post(String endpoint, Object body) {
        return request.body(body).when().post(endpoint).then().extract().response();
    }

    public Response put(String endpoint, Object body) {
        return request.body(body).when().put(endpoint).then().extract().response();
    }

    public Response delete(String endpoint) {
        return request.when().delete(endpoint).then().extract().response();
    }
}
