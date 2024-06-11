package mechanicStock.classes;

import java.sql.*;
import java.util.ArrayList;
import mechanicStock.classes.Product;
import mechanicStock.classes.Vehicle;

public class Stock {
    private int stockID;
    public void setStockID(int ID) { this.stockID = ID; } public int getStockID() { return stockID; }

    private int productID;
    public void setProductID(int ID) { this.productID = ID; } public int getProductID() { return productID; }

    private int vehicleID;
    public void setVehicleID(int ID) { this.vehicleID = ID; } public int getVehicleID() { return vehicleID; }

    private int stockQuantity;
    public void setStockQuantity(int Quantity) { this.stockQuantity = Quantity; } public int getStockQuantity() { return stockQuantity; }

    private int buyPrice;
    public void setBuyPrice(int Price) { this.buyPrice = Price; } public int getBuyPrice() { return buyPrice; }

    private int sellPrice;
    public void setSellPrice(int Price) { this.sellPrice = Price; } public int getSellPrice() { return sellPrice; }

    public Product product;
    public Vehicle vehicle;

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
}
