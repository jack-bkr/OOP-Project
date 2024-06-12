package mechanicStock.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;

import mechanicStock.classes.*;

public class AddController {
    // Attributes
    File imageFile;
    String table;

    // FXML
    @FXML private Label imageSelectLabel;
    @FXML private Button imageSelectButton;
    @FXML private Label imageNameLabel;
    @FXML private Label titleLabel;
    @FXML private Button addButton;

    // User
    @FXML private Label usernameLabel;
    @FXML private TextField usernameTextField;
    @FXML private Label passwordLabel;
    @FXML private PasswordField passwordTextField;
    @FXML private Label confirmPasswordLabel;
    @FXML private PasswordField confirmPasswordTextField;

    // Stock
    @FXML private Label vehicleLabel;
    @FXML private ComboBox<Vehicle> vehicleComboBox;
    @FXML private Label productLabel;
    @FXML private ComboBox<Product> productComboBox;
    @FXML private Label buyPriceLabel;
    @FXML private TextField buyPriceTextField;
    @FXML private Label sellPriceLabel;
    @FXML private TextField sellPriceTextField;
    
    // Product
    @FXML private Label productNameLabel;
    @FXML private TextField productNameTextField;
    @FXML private Label productDescriptionLabel;
    @FXML private TextField productDescriptionTextField;
    
    // Vehicle
    @FXML private Label vehicleMakeLabel;
    @FXML private TextField vehicleMakeTextField;
    @FXML private Label vehicleModelLabel;
    @FXML private TextField vehicleModelTextField;

    public void recieveTable(String table) {
        this.table = table;
        if (this.table == "Users") {
            showUser();
        } else if (this.table == "Stock") {
            // Set up fields for adding a stock item
        } else if (this.table == "Vehicles") {
            // Set up fields for adding a vehicle
        } else if (this.table == "Products") {
            // Set up fields for adding a product
        } else {
            throw new IllegalArgumentException("Invalid table name");
        }
    }

    public void showUser() {
        titleLabel.setText("Register");

        // Show User Fields
        usernameLabel.setVisible(true);
        usernameTextField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordTextField.setVisible(true);
        confirmPasswordLabel.setVisible(true);
        confirmPasswordTextField.setVisible(true);

    }

    public void addUser() {
        
    }

    @FXML
    protected void add(ActionEvent event) {
        if (this.table == "Users") {
            addUser();
        } else if (this.table == "Stock") {
            // Set up fields for adding a stock item
        } else if (this.table == "Vehicles") {
            // Set up fields for adding a vehicle
        } else if (this.table == "Products") {
            // Set up fields for adding a product
        } else {
            throw new IllegalArgumentException("Invalid table name");
        }
    }
    
    @FXML
    protected void numericOnly(KeyEvent keyEvent) {
        char input = keyEvent.getCharacter().charAt(0);
        if (!Character.isDigit(input)) {
            keyEvent.consume();
        }
    }

    @FXML
    protected void selectImage(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            this.imageFile = selectedFile;
            imageNameLabel.setText(this.imageFile.getName());
        }
    }
}
