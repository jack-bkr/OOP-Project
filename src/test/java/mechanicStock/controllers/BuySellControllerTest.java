package mechanicStock.controllers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import mechanicStock.App;

public class BuySellControllerTest extends ApplicationTest {
    BuySellController controller = new BuySellController();
    App app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public void getToBuySell() {
        clickOn("#userNameTextField").write("Jack");
        clickOn("#passwordTextField").write("asdf");
        clickOn("#loginButton");
        clickOn("#table").clickOn("1").clickOn("1");
        clickOn("Buy/Sell");
    }

    @Test
    void testSpinner() {
        getToBuySell();

        clickOn("#quantitySpinner");
        press(KeyCode.DOWN);
        assertNotNull("+£");

        press(KeyCode.UP);
        assertNotNull("£0");

        press(KeyCode.UP);
        assertNotNull("-£");

    }

    @Test
    void testBuySell() {
        getToBuySell();

        clickOn("#quantitySpinner");
        press(KeyCode.UP);
        clickOn("#buySellButton");
        assertNotNull("Transaction Successful");
    }
}
