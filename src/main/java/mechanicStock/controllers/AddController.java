package mechanicStock.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.*;
import java.io.IOException;

import mechanicStock.classes.*;

public class AddController {
    // Attributes
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];
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
    @FXML private Spinner<Integer> buyPriceSpinner;
    @FXML private Label sellPriceLabel;
    @FXML private Spinner<Integer> sellPriceSpinner;
    
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

    public void recieveTable(String table) { //recieves the table name from the previous controller
        this.table = table;
        if (this.table == "Users") { //if the table is users, show the user fields
            showUser();
        } else if (this.table == "Stock") { //if the table is stock, show the stock fields
            showStock();
        } else if (this.table == "Vehicles") { //if the table is vehicles, show the vehicle fields
            showVehicle();
        } else if (this.table == "Products") { //if the table is products, show the product fields
            showProduct();
        } else {
            throw new IllegalArgumentException("Invalid table name");
        }
    }

    public void showUser() { //shows the user fields
        titleLabel.setText("Register");

        // Show User Fields
        usernameLabel.setVisible(true);
        usernameTextField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordTextField.setVisible(true);
        confirmPasswordLabel.setVisible(true);
        confirmPasswordTextField.setVisible(true);

    }

    public void showStock() { //shows the stock fields
        titleLabel.setText("Add Stock");

        // Show Stock Fields
        vehicleLabel.setVisible(true);
        vehicleComboBox.setVisible(true);
        productLabel.setVisible(true);
        productComboBox.setVisible(true);
        buyPriceLabel.setVisible(true);
        buyPriceSpinner.setVisible(true);
        sellPriceLabel.setVisible(true);
        sellPriceSpinner.setVisible(true);

        // Set Spinner Values
        SpinnerValueFactory<Integer> buyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1);
        SpinnerValueFactory<Integer> sellValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1);
        buyPriceSpinner.setValueFactory(buyValueFactory);
        sellPriceSpinner.setValueFactory(sellValueFactory);

        populateComboBoxes();
    }

    public void showVehicle() { //shows the vehicle fields
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

    public void showProduct() { //shows the product fields
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

    public void populateComboBoxes() { //populates the comboboxes with the vehicles and products
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Vehicle vehicle : Vehicle.getAllVehicles()) { //for each vehicle, add it to the vehicles combobox
            vehicles.add(vehicle);
        }

        for (Product product : Product.getAllProducts()) { //for each product, add it to the products combobox
            products.add(product);
        }

        vehicleComboBox.setItems(vehicles);
        vehicleComboBox.selectionModelProperty().get().selectFirst();
        productComboBox.setItems(products);
        productComboBox.selectionModelProperty().get().selectFirst();
    }

    public void addUser() { //adds a user
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) { //if any of the fields are empty, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) { //if the passwords do not match, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passwords do not match");
            alert.showAndWait();
            return;
        }

        if (User.registerUser(username, password)) { //if the user is registered successfully, show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("User registered successfully, awaiting admin approval");
            alert.showAndWait();
        } else { //if the user could not be registered, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User could not be registered");
            alert.showAndWait();
        }
    }

    public void addStock() { //adds stock
        Vehicle vehicle = vehicleComboBox.getValue();
        Product product = productComboBox.getValue();
        int buyPrice = buyPriceSpinner.getValue();
        int sellPrice = sellPriceSpinner.getValue();

        if (vehicle == null || product == null || buyPrice == 0 || sellPrice == 0) { //if any of the fields are empty, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
            return;
        }

        if (Stock.addStock(vehicle.getVehicleID(), product.getProductID(), 0, buyPrice, sellPrice)) { //if the stock is added successfully, show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Stock added successfully");
            alert.showAndWait();
        } else { //if the stock could not be added, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Stock could not be added");
            alert.showAndWait();
        }
    }

    public void addVehicle() { //adds a vehicle
        String make = vehicleMakeTextField.getText();
        String model = vehicleModelTextField.getText();
        int vehicleID;

        if (make.isEmpty() || model.isEmpty() || this.imageFile == null) { //if any of the fields are empty, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
            return;
        }

        vehicleID = Vehicle.addVehicle(make, model); //adds the vehicle to the database

        if (vehicleID > 0) { //if the vehicle is added successfully, show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Vehicle added successfully");
            alert.showAndWait();

            Path dest = Paths.get(path, "img", "vehicle", vehicleID + ".png");
        
            try { //copies the image to the img/vehicle folder
                Files.copy(this.imageFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { //if the vehicle could not be added, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Vehicle could not be added");
            alert.showAndWait();
        }
    }

    public void addProduct() { //adds a product
        String name = productNameTextField.getText();
        String description = productDescriptionTextField.getText();
        int productID;

        if (name.isEmpty() || description.isEmpty() || this.imageFile == null) { //if any of the fields are empty, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
            return;
        }

        productID = Product.addProduct(name, description); //adds the product to the database

        if (productID > 0) { //if the product is added successfully, show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Product added successfully");
            alert.showAndWait();

            Path dest = Paths.get(path, "img", "product", productID + ".png");

            try { //copies the image to the img/product folder
                Files.copy(this.imageFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } else { //if the product could not be added, show an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Product could not be added");
            alert.showAndWait();
        }
    }

    @FXML
    protected void add(ActionEvent event) { //adds the item, depending on the table
        if (this.table == "Users") {
            addUser();
        } else if (this.table == "Stock") {
            addStock();
        } else if (this.table == "Vehicles") {
            addVehicle();
        } else if (this.table == "Products") {
            addProduct();
        } else {
            throw new IllegalArgumentException("Invalid table name");
        }
    }

    @FXML
    protected void selectImage(ActionEvent event) { //opens a file chooser to select an image
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
