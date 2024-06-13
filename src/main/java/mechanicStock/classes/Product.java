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

public class Product {
    private static String path = Product.class.getProtectionDomain().getCodeSource().getLocation().getPath();

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

    public void loadInfo() {
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveProduct(this);

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
            is = new FileInputStream(path + "img/product/" + this.productID + ".png");
            return new Image(new BufferedInputStream(is), width, height, true, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Product getProductByID(int ID) {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "SELECT * FROM Products WHERE productID = " + ID + ";";
        Connection conn = null;
        Statement stmt = null;
        Product product = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                product = new Product(rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("productDescription"));
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
            }
        }
        return product;
    }

    public static ArrayList<Product> getAllProducts() {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "SELECT * FROM Products;";
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                products.add(new Product(rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("productDescription")));
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
            }
        }

        return products;
    }
    
    public static int addProduct(String name, String description) {
        String dbURL = "jdbc:sqlite:" + path + "mechanicStockDB.sqlite";
        String query = "INSERT INTO Products (productName, productDescription) VALUES ('" + name + "', '" + description + "');";
        Connection conn = null;
        Statement stmt = null;
        int productID = -1;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                productID = rs.getInt(1);
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
            }
        }

        return productID;
    }
}
