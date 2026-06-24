package com.restassured.base;

import com.restassured.config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setup() {
        String baseUri = ConfigReader.get("base.uri");
        RestAssured.baseURI = baseUri;
    }
}
