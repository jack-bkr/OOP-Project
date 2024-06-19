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
import mechanicStock.controllers.dbController;

public class Vehicle {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];

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

    public void loadInfo(User user) {
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveCurrentUser(user);
            controller.recieveVehicle(this);

            Scene changeScene = new Scene(root, 400, 450);
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
            is = new FileInputStream(path + "/img/vehicle/" + this.vehicleID + ".png");
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
        String query = "DELETE FROM Stock WHERE vehicleID = " + this.vehicleID + ";" +
        "DELETE FROM Vehicles WHERE vehicleID = " + this.vehicleID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        boolean success = false;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            success = true;
        }

        dbController.closeConnection(conn);
        return success;
    }

    public static Vehicle getVehicleByID(int ID) {
        String query = "SELECT * FROM Vehicles WHERE vehicleID = " + ID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        Vehicle vehicle = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                vehicle = new Vehicle(rs.getInt("vehicleID"),
                        rs.getString("vehicleMake"),
                        rs.getString("vehicleModel"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return vehicle;
    }

    public static ArrayList<Vehicle> getAllVehicles() {
        String query = "SELECT * FROM Vehicles;";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt("vehicleID"),
                        rs.getString("vehicleMake"),
                        rs.getString("vehicleModel"));
                vehicles.add(vehicle);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return vehicles;

    }
    
    public static int addVehicle(String make, String model) {
        String query = "INSERT INTO Vehicles (vehicleMake, vehicleModel) VALUES ('" + make + "', '" + model + "');";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int vehicleID = -1;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                vehicleID = rs.getInt(1);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return vehicleID;
    }
}