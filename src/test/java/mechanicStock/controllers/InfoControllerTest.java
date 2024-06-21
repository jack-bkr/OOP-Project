package mechanicStock.controllers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import mechanicStock.App;
import mechanicStock.classes.Product;
import mechanicStock.classes.Stock;
import mechanicStock.classes.User;
import mechanicStock.classes.Vehicle;

public class InfoControllerTest extends ApplicationTest {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];
    App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public void getToStockInfo(String item) {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("#table").clickOn(item).clickOn(item);
    }

    public void getToProductInfo(String product) {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("View Parts");
        clickOn("#viewTable").clickOn(product).clickOn(product);
    }

    public void getToVehicleInfo(String vehicle) {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("View Vehicles");
        clickOn("#viewTable").clickOn(vehicle).clickOn(vehicle);
    }
    
    public void getToUserInfo(String user) {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("View Users");
        clickOn("#viewTable").clickOn(user).clickOn(user);
    }

    @Test
    void testStockInfo() {
        getToStockInfo("1");
        assertNotNull(lookup("Vehicle ID:").query());
    }

    @Test
    void testUserInfo() {
        getToUserInfo("Jack");
        assertNotNull(lookup("User Name:").query());
    }

    @Test
    void testProductInfo() {
        getToStockInfo("1");
        clickOn("Product Info");
        assertNotNull(lookup("Product Name:").query());
    }

    @Test
    void testVehicleInfo() {
        getToStockInfo("1");
        clickOn("Vehicle Info");
        assertNotNull(lookup("Vehicle Make:").query());
    }

    @Test
    void testDeleteStock() {
        int pID = Product.addProduct("test", "test");
        int vID = Vehicle.addVehicle("test", "test");
        Stock.addStock(vID, pID, 1, 1, 1);
        getToStockInfo("test test");
        clickOn("Delete");
        clickOn("OK");
        assertNotNull("Item Deleted");
        Vehicle.getVehicleByID(vID).deleteItem();
        Product.getProductByID(pID).deleteItem();
    }

    @Test
    void testDeleteProduct() {
        Product.addProduct("test", "test");
        getToProductInfo("test");
        clickOn("Delete");
        clickOn("OK");
        assertNotNull("Product Deleted");
    }

    @Test
    void testDeleteVehicle() {
        Vehicle.addVehicle("test", "test");
        getToVehicleInfo("test");
        clickOn("Delete");
        clickOn("OK");
        assertNotNull("Vehicle Deleted");
    }

    @Test
    void testDeleteUser() {
        User.registerUser("test", "test");
        getToUserInfo("test");
        clickOn("Delete");
        clickOn("OK");
        assertNotNull("User Deleted");
    }

    @Test
    void testBuySell() {
        getToStockInfo("1");
        clickOn("Buy/Sell");
        assertNotNull(lookup("Quantity:").query());
    }

    @Test
    void testPrint() {
        getToStockInfo("1");
        clickOn("Print");
        File file = new File(path + "\\Stock-1.txt");
        assertTrue(file.isFile());
    }

    @Test
    void testToggleAdmin() {
        User.registerUser("test", "test");
        getToUserInfo("test");
        clickOn("Make Admin");
        assertNotNull("User Updated");
        User.getUserByUserName("test").deleteItem();
    }

    @Test
    void testToggleActive() {
        User.registerUser("test", "test");
        getToUserInfo("test");
        clickOn("Approve");
        assertNotNull("User Updated");
        User.getUserByUserName("test").deleteItem();
    }

}
