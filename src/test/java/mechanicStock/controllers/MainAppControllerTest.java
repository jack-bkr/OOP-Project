package mechanicStock.controllers;

import java.io.File;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import mechanicStock.App;

public class MainAppControllerTest extends ApplicationTest {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];
    private App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public void getToMainApp() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
    }

    @Test
    void testViewParts() {
        getToMainApp();
        clickOn("View Parts");
        assertNotNull(lookup("Shock Absorber").query());
    }
    
    @Test
    void testViewVehicles() {
        getToMainApp();
        clickOn("View Vehicles");
        assertNotNull(lookup("Volkswagen").query());
    }

    @Test
    void testViewUsers() {
        getToMainApp();
        clickOn("View Users");
        assertNotNull(lookup("Jack").query());
    }

    @Test
    void testItemInfo() {
        getToMainApp();
        clickOn("#table").clickOn("1").clickOn("1");
        assertNotNull(lookup("Product ID:").query());
    }

    @Test
    void testLogout() {
        getToMainApp();
        clickOn("#logoutButton");
        assertNotNull(lookup("#loginButton").query());
    }

    @Test
    void testAddStock() {
        getToMainApp();
        clickOn("Add Stock");
        assertNotNull(lookup("Product:").query());
    }

    @Test
    void testPrintAllStock() {
        getToMainApp();
        clickOn("Print All Stock Information");
        File file = new File(path + "\\Stock-All.txt");
        assertTrue(file.isFile());
    }

    @Test
    void testSearch() {
        getToMainApp();
        clickOn("#searchField").write("Golf");
        assertNotNull(lookup("Golf").query());
        clickOn("#searchField").push(KeyCode.BACK_SPACE);
        clickOn("#searchField").push(KeyCode.BACK_SPACE);
        clickOn("#searchField").push(KeyCode.BACK_SPACE);
        clickOn("#searchField").push(KeyCode.BACK_SPACE);
        clickOn("#searchField").write("Shock Absorber");
        assertNotNull(lookup("Shock Absorber").query());
    }
}
