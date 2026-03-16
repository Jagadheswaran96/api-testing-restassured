package validators;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    public static void validateStatus(Response response, int expectedStatus) {

        response.then().statusCode(expectedStatus);
    }

    public static void validateHeaders(Response response) {

        response.then()
                .header("Content-Type", containsString("application/json"));
    }

    public static void validateField(Response response, String field, Object value) {

        response.then().body(field, equalTo(value));
    }
}