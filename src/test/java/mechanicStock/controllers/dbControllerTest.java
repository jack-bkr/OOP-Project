package mechanicStock.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class dbControllerTest {
    @Test
    void testGetPath() {
        String path = dbController.getPath();
        assertNotNull(path);
    }

    @Test
    void testGetConnection() {
        assertNotNull(dbController.openConnection());
    }

    @Test
    void testCloseConnection() {
        dbController.closeConnection(dbController.openConnection());
    }
}
