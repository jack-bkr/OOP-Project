package mechanicStock.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class dbController {
    public static String getPath() {
        URL rootPath = dbController.class.getProtectionDomain().getCodeSource().getLocation();
        String URI = rootPath.toString();
        String[] currentPath = URI.split("mechanicStock.jar");
        currentPath[0] = currentPath[0].replaceAll("%20", " ");
        return currentPath[0];
    }
        
    public static Connection openConnection() {
        String dbURL = "jdbc:sqlite:" + getPath() + "mechanicStockDB.sqlite";
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
