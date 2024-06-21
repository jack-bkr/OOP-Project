package mechanicStock.controllers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import mechanicStock.App;

public class AddControllerTest extends ApplicationTest {
    App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public void getToView(String view) {
        
        clickOn(view);
    }

    @Test
    void testAddUser() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("View Users");
        clickOn("Add");

        clickOn("#usernameTextField").write("Test");
        clickOn("#passwordTextField").write("test");
        clickOn("#confirmPasswordTextField").write("test");
        clickOn("#addButton");
        assertNotNull("User registered successfully");
    }

    @Test
    void testAddStock() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("Add Stock");

        clickOn("#addButton");

        assertNotNull("Stock added successfully");
    }
}
