package mechanicStock.controllers;

import java.io.File;
import java.sql.*;

public class dbController {
    public static String getPath() { //gets the path of the jar file
        String currentPath = "";
        try{
            File file = new File(dbController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String URI = file.getParent().toString();
            currentPath = URI.replaceAll("%20", " ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return currentPath;
    }
        
    public static Connection openConnection() { //opens a connection to the database
        String dbURL = "jdbc:sqlite:" + getPath() + "/mechanicStockDB.sqlite";
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

    public static void closeConnection(Connection conn) { //closes the connection to the database
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
