package mechanicStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ViewController {
    @FXML
    Label welcomeLabel;

    User user;
    boolean isAdmin;
    String table;
    int selectedID;

    public void recieveSender(User User, String Table) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.isAdmin();
        table = Table;
    }

    public void newScene(String fxmlString, int width, int height) {
        
    }

    @FXML 
    protected void handleAddButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable(table);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleEditButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Edit.fxml"));
            Parent root = loader.load();

            EditController controller = loader.getController();
            controller.recieveTable(table, selectedID);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleDeleteButton(ActionEvent event) {
    }
}