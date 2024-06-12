package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mechanicStock.controllers.InfoController;

public class User {
    // Attributes; getters and setters

    private int userID;
    public void setUserID(int ID) { this.userID = ID; } public int getUserID() { return userID; }

    private String userName;
    public void setUserName(String Name) { this.userName = Name; } public String getUserName() { return userName; }

    private String userPassword;
    public void setUserPassword(String Password) { this.userPassword = Password; } public String getUserPassword() { return userPassword; }

    private boolean isAdmin;
    public void setIsAdmin(boolean Admin) { this.isAdmin = Admin; } public boolean getIsAdmin() { return isAdmin; }

    private Timestamp dateRegistered;
    public Timestamp getDateRegistered() { return dateRegistered; }

    // Constructor
    public User(int userID, String userName, String userPassword, boolean isAdmin, Timestamp dateRegistered) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
        this.dateRegistered = dateRegistered;
    }
    
    // Checks if password is correctc
    public boolean checkPassword(String password) {
        return userPassword.equals(password);
    }

    public void loadInfo() {
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveUser(this);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUserName(String userName) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Users WHERE userName = '" + userName + "';";
        Connection conn = null;
        Statement stmt = null;
        User user = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"), rs.getTimestamp("dateRegistered"));
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
        return user;
    }

    public static User getUserByID(int ID) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Users WHERE userID = " + ID + ";";
        Connection conn = null;
        Statement stmt = null;
        User user = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"),
                        rs.getTimestamp("dateRegistered"));
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
        return user;
    }
    
    public static ArrayList<User> getAllUsers() {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Users;";
        Connection conn = null;
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getBoolean("isAdmin"),
                        rs.getTimestamp("dateRegistered"));
                users.add(user);
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
        return users;
    }
}
