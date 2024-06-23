package mechanicStock.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    @FXML private PasswordField passwordTextField;
    

    public void initialize() {
        File f = new File(dbController.getPath() + "\\mechanicStockDB.sqlite");
        ArrayList<InputStream> vIS = new ArrayList<>();
        ArrayList<InputStream> pIS = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            vIS.add(getClass().getClassLoader().getResourceAsStream("img/vehicle/" + i + ".png"));
        }
        for (int i = 1; i <= 8; i++) {
            pIS.add(getClass().getClassLoader().getResourceAsStream("img/product/" + i + ".png"));
        }

        if (!f.exists()) {
            try {
                InputStream dbIS = getClass().getClassLoader().getResourceAsStream("mechanicStockDB.sqlite");
                Files.copy(dbIS, Paths.get(dbController.getPath() + "/mechanicStockDB.sqlite"));
                Files.createDirectories(Paths.get(dbController.getPath() + "/img/product"));
                Files.createDirectories(Paths.get(dbController.getPath() + "/img/vehicle"));
                for (int i = 1; i <= 5; i++) {
                    Files.copy(vIS.get(i - 1), Paths.get(dbController.getPath() + "/img/vehicle/" + i + ".png"));
                }
                for (int i = 1; i <= 8; i++) {
                    Files.copy(pIS.get(i - 1), Paths.get(dbController.getPath() + "/img/product/" + i + ".png"));
                }
            } catch (IOException e) {
                throw new RuntimeException("Error creating database file", e);
            }
        }
    }

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

        if (!user.getAdminApproved()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Account Not Approved");
            alert.setContentText("Your account has not been approved by an admin yet. Please try again later.");
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
