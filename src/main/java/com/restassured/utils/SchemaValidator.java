package com.restassured.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

public class SchemaValidator {
    public static void validate(Response response, String schemaPath) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }
}