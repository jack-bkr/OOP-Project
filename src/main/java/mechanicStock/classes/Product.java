package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;

public class Product{
    private int productID;
    public void setProductID(int productID) { this.productID = productID; } public int getProductID() { return productID; } 

    private String productName;
    public void setProductName(String productName) { this.productName = productName; } public String getProductName() { return productName; } 

    private String productDescription;
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; } public String getProductDescription() { return productDescription; } 
    
    public Product(int ID, String Name, String Description) {
        this.productID = ID;
        this.productName = Name;
        this.productDescription = Description;
    }

    

    public static Product getProductByID(int ID) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
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
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
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
}
