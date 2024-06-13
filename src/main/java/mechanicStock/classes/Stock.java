package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mechanicStock.classes.Product;
import mechanicStock.classes.Vehicle;
import mechanicStock.controllers.InfoController;

public class Stock {
    // Attributes; getters

    private int stockID; public int getStockID() { return stockID; }
    private int productID; public int getProductID() { return productID; }
    private int vehicleID; public int getVehicleID() { return vehicleID; }
    private int stockQuantity; public int getStockQuantity() { return stockQuantity; }
    private int buyPrice; public int getBuyPrice() { return buyPrice; }

    private int sellPrice;
    public void setSellPrice(int Price) { this.sellPrice = Price; } public int getSellPrice() { return sellPrice; }

    public Product product;
    public Vehicle vehicle;

    // Constructor
    public Stock(int ID, int pID, int vID, int Quantity, int BuyPrice, int SellPrice) {
        this.stockID = ID;
        this.productID = pID;
        this.vehicleID = vID;
        this.stockQuantity = Quantity;
        this.buyPrice = BuyPrice;
        this.sellPrice = SellPrice;
        this.product = Product.getProductByID(pID);
        this.vehicle = Vehicle.getVehicleByID(vID);
    }

    public void loadInfo() {
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveStock(this);

            Scene changeScene = new Scene(root, 400, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stock getStockByID(int ID) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Stock WHERE stockID = " + ID + ";";
        Connection conn = null;
        Statement stmt = null;
        Stock stock = null;

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                stock = new Stock(rs.getInt("stockID"),
                        rs.getInt("productID"),
                        rs.getInt("vehicleID"),
                        rs.getInt("stockQuantity"),
                        rs.getInt("buyPrice"),
                        rs.getInt("sellPrice"));
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

        return stock;
    }
    
    public static ArrayList<Stock> getAllStock() {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Stock;";
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                stocks.add(new Stock(rs.getInt("stockID"),
                        rs.getInt("productID"),
                        rs.getInt("vehicleID"),
                        rs.getInt("stockQuantity"),
                        rs.getInt("buyPrice"),
                        rs.getInt("sellPrice")));
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

        return stocks;
    }
    
    public static boolean addStock(int vID, int pID, int quantity, int buyPrice, int sellPrice) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "INSERT INTO Stock (productID, vehicleID, stockQuantity, buyPrice, sellPrice) VALUES (" + pID + ", " + vID + ", " + quantity + ", " + buyPrice + ", " + sellPrice + ");";
        Connection conn = null;
        Statement stmt = null;
        boolean success = false;

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
                    success = true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return success;
        
    }
}
