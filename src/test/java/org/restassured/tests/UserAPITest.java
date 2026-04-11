package org.restassured.tests;

import api.UserAPI;
import org.restassured.base.BaseTest;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import validators.*;

public class UserAPITest {

    @BeforeClass
    public static void init() {

        BaseTest.setup();
    }

    @Test
    public void verifyUserAPI() throws Exception {

        int userId = 2;

        Response response = UserAPI.getUser(userId);

        ResponseValidator.validateStatus(response,200);

        ResponseValidator.validateHeaders(response);
        
        ResponseValidator.validateField(response, "name", "John");

        SchemaValidator.validateSchema(response,"schemas/user-schema.json");

        BusinessValidator.validateUserId(response,userId);

        DatabaseValidator.validateUserEmail(response,userId);

    }
}
