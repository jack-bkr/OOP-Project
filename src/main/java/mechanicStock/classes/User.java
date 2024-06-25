package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mechanicStock.controllers.InfoController;
import mechanicStock.controllers.dbController;

public class User {

    // Attributes; getters

    private int userID; public int getUserID() { return userID; }
    private String userName; public String getUserName() { return userName; }
    private String userPassword; public String getUserPassword() { return userPassword; }
    private boolean isAdmin; public boolean getIsAdmin() { return isAdmin; }
    private Timestamp dateRegistered; public Timestamp getDateRegistered() { return dateRegistered; }
    private boolean adminApproved; public boolean getAdminApproved() { return adminApproved; }

    // Constructor
    public User(int userID, String userName, String userPassword, boolean isAdmin, Timestamp dateRegistered, boolean adminApproved) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
        this.dateRegistered = dateRegistered;
        this.adminApproved = adminApproved;
    }
    
    // Checks if password is correctc
    public boolean checkPassword(String password) {
        return userPassword.equals(password);
    }

    public void loadInfo(User user) { //loads the info page for the user
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveCurrentUser(user);
            controller.recieveUser(this);

            Scene changeScene = new Scene(root, 400, 450);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteItem() { //deletes the user from the database
        String query = "DELETE FROM Users WHERE userID = " + this.userID + ";";
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
        return success; //returns true if the User was deleted successfully
    }

    public boolean toggleAdmin() { //toggles the admin status of the user
        String query = "UPDATE Users SET isAdmin = " + !this.isAdmin + " WHERE userID = " + this.userID + ";";
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
            this.isAdmin = !this.isAdmin; //toggles the admin status, in the class to reflect the change
        }

        dbController.closeConnection(conn);
        return success; //returns true if the user's admin status was toggled successfully
    }

    public boolean approveUser() { //approves the user
        String query = "UPDATE Users SET adminApproved = TRUE WHERE userID = " + this.userID + ";";
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
            this.adminApproved = true;
        }

        dbController.closeConnection(conn);
        return success; //returns true if the user was approved successfully
    }

    public static User getUserByUserName(String userName) { //gets a user by their username
        String query = "SELECT * FROM Users WHERE userName = '" + userName + "';";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        User user = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getInt("userID"), //creates a new user object with the data from the database
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"),
                        rs.getTimestamp("dateRegistered"),
                        rs.getBoolean("adminApproved"));
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dbController.closeConnection(conn);
        return user;
    }

    public static User getUserByID(int ID) { //gets a user by their ID
        String query = "SELECT * FROM Users WHERE userID = " + ID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        User user = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getInt("userID"), //creates a new user object with the data from the database
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"),
                        rs.getTimestamp("dateRegistered"),
                        rs.getBoolean("adminApproved"));
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return user;
    }
    
    public static ArrayList<User> getAllUsers() { //gets all users from the database
        String query = "SELECT * FROM Users;";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("userID"), //creates a new user object with the data from the database
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"),
                        rs.getTimestamp("dateRegistered"),
                        rs.getBoolean("adminApproved"));
                users.add(user); //adds the user to the arraylist
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return users;
    }

    public static boolean registerUser(String username, String password) { //registers a new user
        String query = "INSERT INTO Users (userName, userPassword, isAdmin, dateRegistered, adminApproved) VALUES ('" + username + "', '" + password + "', FALSE, CURRENT_TIMESTAMP, FALSE);";
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
        return success; //returns true if the user was registered successfully
    }
}
