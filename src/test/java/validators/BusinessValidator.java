package validators;

import io.restassured.response.Response;
import org.junit.Assert;

public class BusinessValidator {

    public static void validateUserId(Response response, int expectedId) {

        int id = response.jsonPath().getInt("data.id");

        Assert.assertEquals(expectedId, id);
    }

}