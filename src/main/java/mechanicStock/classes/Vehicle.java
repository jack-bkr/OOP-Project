package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mechanicStock.controllers.InfoController;

public class Vehicle {
    // Attributes; getters and setters

    int vehicleID;
    public void setVehicleID(int ID) { this.vehicleID = ID; } public int getVehicleID() { return vehicleID; }

    String vehicleMake;
    public void setVehicleMake(String Make) { this.vehicleMake = Make; } public String getVehicleMake() { return vehicleMake; }

    String vehicleModel;
    public void setVehicleModel(String Model) { this.vehicleModel = Model; } public String getVehicleModel() { return vehicleModel; }

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
        return new Image("/img/vehicle/" + this.vehicleID + ".png", 0, 100, true, false);
    }

    public static Vehicle getVehicleByID(int ID) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
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
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
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
}