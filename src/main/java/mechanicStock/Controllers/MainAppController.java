package mechanicStock.Controllers;

import java.util.ArrayList;

import com.google.common.collect.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mechanicStock.Classes.*;

public class MainAppController {
    @FXML 
    Label welcomeLabel;
    @FXML TableView table;

    User user;
    boolean isAdmin;
    
    public void recieveUser(User User) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
        
    }
    
    @FXML 
    protected void handleLogoutButton(ActionEvent event) {
        try { 
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Login.fxml"));
            Parent root = loader.load();

            Scene changeScene = new Scene(root, 600, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    protected void handleViewProductsButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user);
            controller.recieveProducts(Product.getAllProducts());

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Products");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleViewVehiclesButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user);
            controller.recieveVehicles(Vehicle.getAllVehicles());

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Vehicles");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleViewUsersButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user);
            controller.recieveUsers(User.getAllUsers());

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Users");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handlePrintAllButton(ActionEvent event) {

    }
    
    @FXML
    protected void handleAddStockButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable("user");

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Add Stock");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleAddUserButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable("user");

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Add User");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
