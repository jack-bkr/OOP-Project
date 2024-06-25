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

public class Product {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];

    // Attributes; getters

    private int productID; public int getProductID() { return productID; } 
    private String productName; public String getProductName() { return productName; } 
    private String productDescription; public String getProductDescription() { return productDescription; } 

    // Constructor
    public Product(int ID, String Name, String Description) {
        this.productID = ID;
        this.productName = Name;
        this.productDescription = Description;
    }

    public void loadInfo(User user) { //loads the info page for the product
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveCurrentUser(user);
            controller.recieveProduct(this);

            Scene changeScene = new Scene(root, 400, 450);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage(int width, int height) { //returns the image of the product
        InputStream is;
        try {
            is = new FileInputStream(path + "/img/product/" + this.productID + ".png");
            return new Image(new BufferedInputStream(is), width, height, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() { //overrides the toString method to return the product name, this is for comboboxes
        return this.productName;
    }

    public boolean deleteItem() { //deletes the product from the database
        String query = "DELETE FROM Stock WHERE productID = " + this.productID + ";" +
        "DELETE FROM Products WHERE productID = " + this.productID + ";";
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

        return success; //returns true if the product was deleted successfully
    }

    public static Product getProductByID(int ID) {
        String query = "SELECT * FROM Products WHERE productID = " + ID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        Product product = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                product = new Product(rs.getInt("productID"), //creates a new product object with the data from the database
                        rs.getString("productName"),
                        rs.getString("productDescription"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dbController.closeConnection(conn);
        return product;
    }

    public static ArrayList<Product> getAllProducts() {
        String query = "SELECT * FROM Products;";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        ArrayList<Product> products = new ArrayList<Product>();

        
        try {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) { //for each product in the database, create a new product object and add it to the arraylist
                products.add(new Product(rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("productDescription")));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return products;
    }
    
    public static int addProduct(String name, String description) { //adds a new product to the database
        String query = "INSERT INTO Products (productName, productDescription) VALUES ('" + name + "', '" + description + "');";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        int productID = -1;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            ResultSet rs = stmt.getGeneratedKeys(); //gets the productID of the new product
            if (rs.next()) {
                productID = rs.getInt(1);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return productID; //returns the productID of the new product
    }
}
