package mechanicStock.controllers;

import java.sql.*;

public class dbController {
    public static Connection openConnection() {
        String dbURL = "jdbc:sqlite:" + dbController.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "mechanicStockDB.sqlite";
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
