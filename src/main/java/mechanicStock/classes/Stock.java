package mechanicStock.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mechanicStock.controllers.BuySellController;
import mechanicStock.controllers.InfoController;
import mechanicStock.controllers.dbController;

public class Stock {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];

    // Attributes; getters

    private int stockID; public int getStockID() { return stockID; }
    private int productID; public int getProductID() { return productID; }
    private int vehicleID; public int getVehicleID() { return vehicleID; }
    private int stockQuantity; public int getStockQuantity() { return stockQuantity; }
    private int buyPrice; public int getBuyPrice() { return buyPrice; }
    private int sellPrice; public int getSellPrice() { return sellPrice; }

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

    public void loadInfo(User user) { //loads the info page for the stock
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Info.fxml"));
            Parent root = loader.load();

            InfoController controller = loader.getController();
            controller.recieveCurrentUser(user);
            controller.recieveStock(this);

            Scene changeScene = new Scene(root, 400, 450);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Item Info");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteItem() { //deletes the stock item from the database
        String query = "DELETE FROM Stock WHERE stockID = " + this.stockID + ";";
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
        return success; //returns true if the stock item was deleted successfully
    }

    public void buySell() { //loads the buy/sell page for the stock
        try {
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/BuySell.fxml"));
            Parent root = loader.load();

            BuySellController controller = loader.getController();
            controller.recieveStock(this);

            Scene changeScene = new Scene(root, 400, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Buy/Sell Item");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setQuantity(int quantity) { //sets the quantity of the stock item, for use in the buy/sell page
        this.stockQuantity = quantity;
        String query = "UPDATE Stock SET stockQuantity = " + quantity + " WHERE stockID = " + this.stockID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        boolean success = false;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);

        return success; //returns true if the quantity was set successfully
    }

    public BufferedWriter writeBuffer(BufferedWriter buffer) {  //writes the stock item info to a BufferedWriter
        try {                                                   //for use in the printInfo method         
            buffer.write("Stock ID: " + this.stockID);
            buffer.newLine();
            buffer.write("Product ID: " + this.productID);
            buffer.newLine();
            buffer.write("Product Name: " + this.product.getProductName());
            buffer.newLine();
            buffer.write("Product Description: " + this.product.getProductDescription());
            buffer.newLine();
            buffer.write("Vehicle ID: " + this.vehicleID);
            buffer.newLine();
            buffer.write("Vehicle Make: " + this.vehicle.getVehicleMake());
            buffer.newLine();
            buffer.write("Vehicle Model: " + this.vehicle.getVehicleModel());
            buffer.newLine();
            buffer.write("Stock Quantity: " + this.stockQuantity);
            buffer.newLine();
            buffer.write("Buy Price: " + this.buyPrice);
            buffer.newLine();
            buffer.write("Sell Price: " + this.sellPrice);
            buffer.newLine();
            buffer.newLine();

            return buffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printInfo() { //prints the stock item info to a text file
        File file = new File(path + "\\Stock-" + this.stockID + ".txt");
        try {
            if (file.createNewFile()) { //creates a new file with the stock ID as the name
                System.out.println("File created: " + file.getPath());
            } else { //if the file already exists, prints a message
                System.out.println("File already exists.");
            }
            
            FileWriter writer = new FileWriter(file);

            BufferedWriter buffer = new BufferedWriter(writer);

            buffer = writeBuffer(buffer); //writes the stock item info to the file

            buffer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Stock getStockByID(int ID) { //returns a stock item by its ID
        String query = "SELECT * FROM Stock WHERE stockID = " + ID + ";";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        Stock stock = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                stock = new Stock(rs.getInt("stockID"), //creates a new stock object with the data from the database
                        rs.getInt("productID"),
                        rs.getInt("vehicleID"),
                        rs.getInt("stockQuantity"),
                        rs.getInt("buyPrice"),
                        rs.getInt("sellPrice"));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return stock;
    }
    
    public static ArrayList<Stock> getAllStock() { //returns an arraylist of all stock items in the database
        String query = "SELECT * FROM Stock;";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                stocks.add(new Stock(rs.getInt("stockID"),  //for each stock item in the database
                        rs.getInt("productID"),             //create a new stock object and add it to the arraylist
                        rs.getInt("vehicleID"),
                        rs.getInt("stockQuantity"),
                        rs.getInt("buyPrice"),
                        rs.getInt("sellPrice")));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return stocks;
    }

    public static int getTotalStockValue() { //returns the total value of all stock items in the database (quantity * sellPrice)
        String query = "SELECT SUM(stockQuantity * sellPrice) FROM Stock;";
        Connection conn = dbController.openConnection();
        Statement stmt = null;
        int totalValue = 0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                totalValue = rs.getInt(1);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbController.closeConnection(conn);
        return totalValue;
    }
    
    public static boolean addStock(int vID, int pID, int quantity, int buyPrice, int sellPrice) { //adds a new stock item to the database
        String query = "INSERT INTO Stock (productID, vehicleID, stockQuantity, buyPrice, sellPrice) VALUES (" + pID
                + ", " + vID + ", " + quantity + ", " + buyPrice + ", " + sellPrice + ");";
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
    
}
