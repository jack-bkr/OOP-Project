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
            showStock();
        } else if (this.table == "Vehicles") {
            showVehicle();
        } else if (this.table == "Products") {
            showProduct();
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

    public void showStock() {
        titleLabel.setText("Add Stock");

        // Show Stock Fields
        vehicleLabel.setVisible(true);
        vehicleComboBox.setVisible(true);
        productLabel.setVisible(true);
        productComboBox.setVisible(true);
        buyPriceLabel.setVisible(true);
        buyPriceTextField.setVisible(true);
        sellPriceLabel.setVisible(true);
        sellPriceTextField.setVisible(true);
    }

    public void showVehicle() {
        titleLabel.setText("Add Vehicle");

        // Show Vehicle Fields
        vehicleMakeLabel.setVisible(true);
        vehicleMakeTextField.setVisible(true);
        vehicleModelLabel.setVisible(true);
        vehicleModelTextField.setVisible(true);

        // Show Image Fields
        imageSelectLabel.setVisible(true);
        imageSelectButton.setVisible(true);
        imageNameLabel.setVisible(true);
    }

    public void showProduct() {
        titleLabel.setText("Add Product");

        // Show Product Fields
        productNameLabel.setVisible(true);
        productNameTextField.setVisible(true);
        productDescriptionLabel.setVisible(true);
        productDescriptionTextField.setVisible(true);

        // Show Image Fields
        imageSelectLabel.setVisible(true);
        imageSelectButton.setVisible(true);
        imageNameLabel.setVisible(true);
    }

    public void addUser() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords do not match");
            alert.showAndWait();
            return;
        }

        if (User.registerUser(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("User registered successfully, awaiting admin approval");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User could not be registered");
            alert.showAndWait();
        }
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
