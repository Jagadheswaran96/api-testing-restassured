package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";

        return DriverManager.getConnection(url, username, password);
    }

}