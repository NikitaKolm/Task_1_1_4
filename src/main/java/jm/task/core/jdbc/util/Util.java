package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/db";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connected to DB");
                return connection;
            }
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            System.out.println("Not connected to DB");
        }
        return null;
    }
}
