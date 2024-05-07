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

    public void newScene(String fxmlString, int width, int height) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlString));
            Parent root = loader.load();
            Scene changeScene = new Scene(root, width, height);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    protected void handleLogoutButton(ActionEvent event) {
        newScene("Login.fxml", 600, 400);
    }
    
    @FXML 
    protected void handleViewPartsButton(ActionEvent event) {
        newScene("ViewParts.fxml", 900, 500);
    }
    
    @FXML
    protected void handleViewVehiclesButton(ActionEvent event) {
        newScene("ViewVehicles.fxml", 900, 500);
    }
    
    @FXML
    protected void handleViewUsersButton(ActionEvent event) {
        newScene("ViewUsers.fxml", 900, 500);
    }
    
    @FXML
    protected void handlePrintAllButton(ActionEvent event) {

    }
    
    @FXML
    protected void handleAddStockButton(ActionEvent event) {
        newScene("AddStock.fxml", 400, 600);
    }
    
    @FXML
    protected void handleAddUserButton(ActionEvent event) {
        newScene("AddUsers.fxml", 400, 600);
    }

}
