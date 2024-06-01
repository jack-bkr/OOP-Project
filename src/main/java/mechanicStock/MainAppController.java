package mechanicStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainAppController {
    @FXML 
    Label welcomeLabel;

    User user;
    boolean isAdmin;
    
    public void recieveUser(User User) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.isAdmin();
    } 

    @FXML 
    protected void handleLogoutButton(ActionEvent event) {
        try { 
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
            Parent root = loader.load();

            Scene changeScene = new Scene(root, 600, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    protected void handleViewPartsButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ViewP-V-U.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Products");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Parts");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleViewVehiclesButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ViewP-V-U.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Vehicles");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ViewP-V-U.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Users");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddStock.fxml"));
            Parent root = loader.load();

            MainAppController controller = loader.getController();
            controller.recieveUser(user);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddUsers.fxml"));
            Parent root = loader.load();

            MainAppController controller = loader.getController();
            controller.recieveUser(user);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Add User");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
