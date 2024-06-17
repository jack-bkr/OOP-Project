package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mechanicStock.controllers.InfoController;

public class Vehicle {
    private static String path = Vehicle.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    // Attributes; getters

    private int vehicleID; public int getVehicleID() { return vehicleID; }
    private String vehicleMake; public String getVehicleMake() { return vehicleMake; }
    private String vehicleModel; public String getVehicleModel() { return vehicleModel; }

    // Constructor
    public Vehicle(int ID, String Make, String Model) {
        this.vehicleID = ID;
        this.vehicleMake = Make;
        this.vehicleModel = Model;
    }

    public void loadInfo() {
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveVehicle(this);

            Scene changeScene = new Scene(root, 400, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage(int width, int height) {
        InputStream is;
        try {
            is = new FileInputStream(path + "img/vehicle/" + this.vehicleID + ".png");
            return new Image(new BufferedInputStream(is), width, height, true, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
    
    @Override
    public String toString() {
        return this.vehicleMake + " " + this.vehicleModel;
    }

    public boolean deleteItem() {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "DELETE FROM Stock WHERE vehicleID = " + this.vehicleID + ";" +
        "DELETE FROM Vehicles WHERE vehicleID = " + this.vehicleID + ";";
        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            stmt.executeUpdate(query);

            stmt.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                conn = null;
            }
        }

        return true;
    }

    public static Vehicle getVehicleByID(int ID) {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "SELECT * FROM Vehicles WHERE vehicleID = " + ID + ";";
        Connection conn = null;
        Statement stmt = null;
        Vehicle vehicle = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                vehicle = new Vehicle(rs.getInt("vehicleID"),
                        rs.getString("vehicleMake"),
                        rs.getString("vehicleModel"));
            }

            stmt.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                conn = null;
            }
        }

        return vehicle;
    }

    public static ArrayList<Vehicle> getAllVehicles() {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "SELECT * FROM Vehicles;";
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt("vehicleID"),
                        rs.getString("vehicleMake"),
                        rs.getString("vehicleModel"));
                vehicles.add(vehicle);
            }

            stmt.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                conn = null;
            }
        }

        return vehicles;

    }
    
    public static int addVehicle(String make, String model) {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "INSERT INTO Vehicles (vehicleMake, vehicleModel) VALUES ('" + make + "', '" + model + "');";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int vehicleID = -1;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                vehicleID = rs.getInt(1);
            }

            stmt.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                conn = null;
            }
        }

        return vehicleID;
    }
}