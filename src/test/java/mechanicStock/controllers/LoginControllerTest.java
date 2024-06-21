package mechanicStock.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import mechanicStock.App;
import mechanicStock.classes.User;

public class LoginControllerTest extends ApplicationTest{
    private App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    @Test
    void testLogin() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        assertNotNull(lookup("#logoutButton").query());
    }

    @Test
    void testIncorrectLogin() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("wrongpassword");
        clickOn("#loginButton");
        assertNotNull(lookup("Invalid Username/Password").query());
    }

    @Test
    void testUnapprovedLogin() {
        User.registerUser("John", "Doe");
        clickOn("#userNameTextField").write("John");
        clickOn("#passwordTextField").write("Doe");
        clickOn("#loginButton");
        assertNotNull(lookup("Account Not Approved").query());
    }

    @Test
    void testRegister() {
        clickOn("#registerButton");
        assertNotNull(lookup("#registerButton").query());
    }
}
