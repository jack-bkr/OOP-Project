package mechanicStock.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mechanicStock.classes.User;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        User user = getUser(userNameTextField.getText());

        if (user == null || !user.checkPassword(passwordTextField.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username/Password");
            alert.setContentText("The Username/Password you entered is incorrect. Please try again.");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainApp.fxml"));
        Parent root = loader.load();

        MainAppController controller = loader.getController();
        controller.recieveUser(user);

        Scene changeScene = new Scene(root, 900, 500);
        changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
        stage.setScene(changeScene);
        stage.setTitle("Mechanic Stock Management System");
        stage.show();
    }

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) throws Exception {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable("Users");

            Scene changeScene = new Scene(root, 325, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Register");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static User getUser(String userName) {
        return User.getUserByUserName(userName);
    }
}