package mechanicStock.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import mechanicStock.classes.*;

public class InfoController {
    // Attributes
    private String table;
    private Stock stock;
    private Product product;
    private Vehicle vehicle;
    private User user;
    private User currentUser;

    // FXML Attributes
    @FXML private Label idValueLabel;

    // Images
    @FXML private Label imageLabel;
    @FXML private ImageView imageView;
    @FXML private Label secondaryImageLabel;
    @FXML private ImageView secondaryImageView;
    
    // Buttons
    @FXML private Button productInfoButton;
    @FXML private Button vehicleInfoButton;
    @FXML private Button approveButton;
    @FXML private Button toggleAdminButton;
    @FXML private Button buySellButton;
    @FXML private Button printButton;
    @FXML private Button deleteButton;

    // Stock
    @FXML private Label productIDLabel;
    @FXML private Label productIDValueLabel;
    @FXML private Label vehicleIDLabel;
    @FXML private Label vehicleIDValueLabel;
    @FXML private Label quantityLabel;
    @FXML private Label quantityValueLabel;
    @FXML private Label buyPriceLabel;
    @FXML private Label buyPriceValueLabel;
    @FXML private Label sellPriceLabel;
    @FXML private Label sellPriceValueLabel;

    // Product
    @FXML private Label productNameLabel;
    @FXML private Label productNameValueLabel;
    @FXML private Label productDescriptionLabel;
    @FXML private Label productDescriptionValueLabel;

    // Vehicle
    @FXML private Label vehicleMakeLabel;
    @FXML private Label vehicleMakeValueLabel;
    @FXML private Label vehicleModelLabel;
    @FXML private Label vehicleModelValueLabel;
    
    // User
    @FXML private Label userNameLabel;
    @FXML private Label userNameValueLabel;
    @FXML private Label isAdminLabel;
    @FXML private Label isAdminValueLabel;
    @FXML private Label isAdminApprovedLabel;
    @FXML private Label isAdminApprovedValueLabel;
    @FXML private Label dateRegisteredLabel;
    @FXML private Label dateRegisteredValueLabel;
    
    public void recieveCurrentUser(User user) {
        this.currentUser = user;
        if (!user.getIsAdmin()) {
            toggleAdminButton.setDisable(true);
            approveButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }
    
    public void recieveStock(Stock stock) {
        this.table = "Stock";
        this.stock = stock;

        // Set Product and Vehicle Info Buttons visible
        productInfoButton.setVisible(true);
        vehicleInfoButton.setVisible(true);
        buySellButton.setVisible(true);
        printButton.setVisible(true);

        // Set Images Visible
        imageLabel.setVisible(true);
        imageView.setVisible(true);
        secondaryImageLabel.setVisible(true);
        secondaryImageView.setVisible(true);

        // Set Image values
        imageLabel.setText("Vehicle Image");
        imageView.setImage(stock.vehicle.getImage(0, 100));

        secondaryImageLabel.setText("Product Image");
        secondaryImageView.setImage(stock.product.getImage(0, 100));

        // Set Stock Label Visible
        productIDLabel.setVisible(true);
        vehicleIDLabel.setVisible(true);
        quantityLabel.setVisible(true);
        buyPriceLabel.setVisible(true);
        sellPriceLabel.setVisible(true);

        // Set Stock Values Visible
        productIDValueLabel.setVisible(true);
        vehicleIDValueLabel.setVisible(true);
        quantityValueLabel.setVisible(true);
        buyPriceValueLabel.setVisible(true);
        sellPriceValueLabel.setVisible(true);

        // Set Stock values
        idValueLabel.setText(String.valueOf(stock.getStockID()));
        productIDValueLabel.setText(String.valueOf(stock.getProductID()));
        vehicleIDValueLabel.setText(String.valueOf(stock.getVehicleID()));
        quantityValueLabel.setText(String.valueOf(stock.getStockQuantity()));
        buyPriceValueLabel.setText(String.valueOf(stock.getBuyPrice()));
        sellPriceValueLabel.setText(String.valueOf(stock.getSellPrice()));
    }

    public void recieveProduct(Product product) {
        this.table = "Product";
        this.product = product;

        // Set Images Visible
        imageLabel.setVisible(true);
        imageView.setVisible(true);

        // Set Image values
        imageLabel.setText("Product Image");
        imageView.setImage(product.getImage(0, 100));
        // Set Product Label Visible
        productNameLabel.setVisible(true);
        productDescriptionLabel.setVisible(true);

        // Set Product Values Visible
        productNameValueLabel.setVisible(true);
        productDescriptionValueLabel.setVisible(true);

        // Set Product values
        idValueLabel.setText(String.valueOf(product.getProductID()));
        productNameValueLabel.setText(product.getProductName());
        productDescriptionValueLabel.setText(product.getProductDescription());
    }

    public void recieveVehicle(Vehicle vehicle) {
        this.table = "Vehicle";
        this.vehicle = vehicle;

        // Set Images Visible
        imageLabel.setVisible(true);
        imageView.setVisible(true);

        // Set Image values
        imageLabel.setText("Vehicle Image");
        imageView.setImage(vehicle.getImage(0, 100));

        // Set Vehicle Label Visible
        vehicleMakeLabel.setVisible(true);
        vehicleModelLabel.setVisible(true);

        // Set Vehicle Values Visible
        vehicleMakeValueLabel.setVisible(true);
        vehicleModelValueLabel.setVisible(true);

        // Set Vehicle values
        idValueLabel.setText(String.valueOf(vehicle.getVehicleID()));
        vehicleMakeValueLabel.setText(vehicle.getVehicleMake());
        vehicleModelValueLabel.setText(vehicle.getVehicleModel());
    }

    public void recieveUser(User user) {
        this.table = "User";
        this.user = user;

        // Set User Label Visible
        userNameLabel.setVisible(true);
        isAdminLabel.setVisible(true);
        isAdminApprovedLabel.setVisible(true);
        dateRegisteredLabel.setVisible(true);

        // Set User Values Visible
        userNameValueLabel.setVisible(true);
        isAdminValueLabel.setVisible(true);
        isAdminApprovedValueLabel.setVisible(true);
        dateRegisteredValueLabel.setVisible(true);

        // Set User Buttons
        if (!user.getAdminApproved()) { approveButton.setVisible(true); }
        toggleAdminButton.setVisible(true);
        if (user.getIsAdmin()) { toggleAdminButton.setText("Remove Admin"); }

        // Set User values
        idValueLabel.setText(String.valueOf(user.getUserID()));
        userNameValueLabel.setText(user.getUserName());
        isAdminValueLabel.setText(String.valueOf(user.getIsAdmin()));
        isAdminApprovedValueLabel.setText(String.valueOf(user.getAdminApproved()));
        dateRegisteredValueLabel.setText(String.valueOf(user.getDateRegistered()));

        if (this.currentUser.getUserID() == user.getUserID()) {
            toggleAdminButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    public void deleteInfo(boolean success) {
        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Item Deleted");
            alert.setContentText("The item has been successfully deleted.");
            alert.showAndWait();
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Item Not Deleted");
            alert.setContentText("The item has not been deleted. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void handleProductInfoButton(ActionEvent event) {
        stock.product.loadInfo(currentUser);
    }

    @FXML
    protected void handleVehicleInfoButton(ActionEvent event) {
        stock.vehicle.loadInfo(currentUser);
    }

    @FXML
    protected void handleToggleAdminButton(ActionEvent event) {
        if (user.toggleAdmin()) {
            isAdminValueLabel.setText(String.valueOf(user.getIsAdmin()));
            if (user.getIsAdmin()) { toggleAdminButton.setText("Remove Admin"); }
            else { toggleAdminButton.setText("Make Admin"); }
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Admin Status Changed");
            alert.setContentText("The user's admin status has been successfully changed.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Admin Status Not Changed");
            alert.setContentText("The user's admin status has not been changed. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void handleApproveButton(ActionEvent event) {
        if (user.approveUser()) {
            isAdminApprovedValueLabel.setText(String.valueOf(user.getAdminApproved()));
            approveButton.setVisible(false);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Approval Status Changed");
            alert.setContentText("This user is now approved.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Approval Not Changed");
            alert.setContentText("The user's approval status has not been changed. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void handleDeleteButton(ActionEvent event) {
        String alertContext = "Are you sure you want to delete ";

        if (table == "Stock") {
            alertContext += "Stock ID: " + idValueLabel.getText();
        } else if (table == "Product") {
            alertContext += productNameValueLabel.getText()
                    + ", it will also delete all Stock associated with this Product.";
        } else if (table == "Vehicle") {
            alertContext += vehicleMakeValueLabel.getText() + " " + vehicleModelValueLabel.getText()
                    + ", it will also delete all Stock associated with this Vehicle.";
        } else if (table == "User") {
            alertContext += userNameValueLabel.getText();
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText(alertContext);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            if (table == "Stock") {
                deleteInfo(stock.deleteItem());
            } else if (table == "Product") {
                deleteInfo(product.deleteItem());
            } else if (table == "Vehicle") {
                deleteInfo(vehicle.deleteItem());
            } else if (table == "User") {
                deleteInfo(user.deleteItem());
            }
        }

    }
    
    @FXML
    protected void handleBuySellButton(ActionEvent event) {
        stock.buySell();
    }

    @FXML
    protected void handlePrintButton(ActionEvent event) {
        stock.printInfo();
    }
}
