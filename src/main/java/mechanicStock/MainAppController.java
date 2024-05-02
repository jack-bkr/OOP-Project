package mechanicStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainAppController {
    @FXML Label welcomeLabel;
    
    public void recieveUsername(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }
    
}
