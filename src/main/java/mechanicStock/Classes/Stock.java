package mechanicStock.Classes;

import java.sql.*;
import java.util.ArrayList;

public class Stock {
    int stockID;
    int productID;
    int vehicleID;
    int stockQuantity;
    int buyPrice;
    int sellPrice;

    public Stock(int ID, int pID, int vID, int Quantity, int BuyPrice, int SellPrice) {
        this.stockID = ID;
        this.productID = pID;
        this.vehicleID = vID;
        this.stockQuantity = Quantity;
        this.buyPrice = BuyPrice;
        this.sellPrice = SellPrice;
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
        ArrayList<Stock> stock = new ArrayList<Stock>();
        
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                stock.add(new Stock(rs.getInt("stockID"),
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

        return stock;
    }
}
