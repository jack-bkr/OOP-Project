package mechanicStock.controllers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import mechanicStock.App;

public class ViewControllerTest extends ApplicationTest{
    App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public void getToView(String view) {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn(view);
    }

    @Test
    void testViewParts() {
        getToView("View Parts");
        assertNotNull(lookup("Shock Absorber").query());
    }

    @Test
    void testPartInfo() {
        getToView("View Parts");
        clickOn("#viewTable").clickOn("Shock Absorber").clickOn("Shock Absorber");
        assertNotNull(lookup("Product Name:").query());
    }

    @Test
    void testAddPart() {
        getToView("View Parts");
        clickOn("Add");
        assertNotNull(lookup("Product Name:").query());
    }

    @Test
    void testPartSearchName() {
        getToView("View Parts");
        clickOn("#viewSearchField").write("Shock Absorber");
        assertNotNull(lookup("Shock Absorber").query());
    }

    @Test
    void testPartSearchDescription() {
        getToView("View Parts");
        clickOn("#viewSearchField").write("Absorbs shocks");
        assertNotNull(lookup("Absorbs shocks").query());
    }

    @Test
    void testViewVehicles() {
        getToView("View Vehicles");
        assertNotNull(lookup("Volkswagen").query());
    }

    @Test
    void testVehicleInfo() {
        getToView("View Vehicles");
        clickOn("#viewTable").clickOn("Golf").clickOn("Golf");
        assertNotNull(lookup("Vehicle Make:").query());
    }

    @Test
    void testAddVehicle() {
        getToView("View Vehicles");
        clickOn("Add");
        assertNotNull(lookup("Vehicle Make:").query());
    }

    @Test
    void testVehicleSearchMake() {
        getToView("View Vehicles");
        clickOn("#viewSearchField").write("Volkswagen");
        assertNotNull(lookup("Volkswagen").query());
    }

    @Test
    void testVehicleSearchModel() {
        getToView("View Vehicles");
        clickOn("#viewSearchField").write("Golf");
        assertNotNull(lookup("Golf").query());
    }

    @Test
    void testViewUsers() {
        getToView("View Users");
        assertNotNull(lookup("Jack").query());
    }

    @Test
    void testUserInfo() {
        getToView("View Users");
        clickOn("#viewTable").clickOn("Jack").clickOn("Jack");
        assertNotNull(lookup("User Name:").query());
    }

    @Test
    void testAddUser() {
        getToView("View Users");
        clickOn("Add");
        assertNotNull(lookup("Username: ").query());
    }

    @Test
    void testUserSearch() {
        getToView("View Users");
        clickOn("#viewSearchField").write("Jack");
        assertNotNull(lookup("Jack").query());
    }

}
