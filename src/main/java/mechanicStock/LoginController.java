package mechanicStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
    @FXML private Button loginButton;
    @FXML private TextField userNameTextField;

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainApp.fxml"));
        Parent root = loader.load();

        MainAppController controller = loader.getController();
        controller.recieveUsername(userNameTextField.getText());

        Scene changeScene = new Scene(root, 900, 500);
        changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
        stage.setScene(changeScene);
        stage.show();        
    }
}
