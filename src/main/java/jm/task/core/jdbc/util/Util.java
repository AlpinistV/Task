package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Objects;


public class Util {

    private static Driver DRIVER;
    private static final String URL = "jdbc:mysql://localhost:3306/connectiontest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Goodgamewp333-";

    public static Connection getConnection() {

        try {
            DRIVER = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            Objects.requireNonNull(getConnection()).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




