package validators;

import database.DBConnection;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseValidator {

    public static void validateUserEmail(Response response, int userId) throws Exception {

        String apiEmail = response.jsonPath().getString("data.email");

        Connection conn = DBConnection.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs =
                stmt.executeQuery("SELECT email FROM users WHERE id=" + userId);

        rs.next();

        String dbEmail = rs.getString("email");

        Assert.assertEquals(apiEmail, dbEmail);

        conn.close();
    }

}