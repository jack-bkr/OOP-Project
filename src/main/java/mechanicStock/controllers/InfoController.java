package mechanicStock.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import mechanicStock.classes.*;

public class InfoController {
    // Attributes
    @FXML
    private Label idValueLabel;

    // Stock
    @FXML
    private Label productIDLabel;
    @FXML
    private Label productIDValueLabel;
    @FXML
    private Label vehicleIDLabel;
    @FXML
    private Label vehicleIDValueLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label quantityValueLabel;
    @FXML
    private Label buyPriceLabel;
    @FXML
    private Label buyPriceValueLabel;
    @FXML
    private Label sellPriceLabel;
    @FXML
    private Label sellPriceValueLabel;

    // Product
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productNameValueLabel;
    @FXML
    private Label productDescriptionLabel;
    @FXML
    private Label productDescriptionValueLabel;

    // Vehicle
    @FXML
    private Label vehicleMakeLabel;
    @FXML
    private Label vehicleMakeValueLabel;
    @FXML
    private Label vehicleModelLabel;
    @FXML
    private Label vehicleModelValueLabel;
    
    // User
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userNameValueLabel;
    @FXML
    private Label isAdminLabel;
    @FXML
    private Label isAdminValueLabel;
    @FXML
    private Label dateRegisteredLabel;
    @FXML
    private Label dateRegisteredValueLabel;
    
    
    public void recieveStock(Stock stock) {
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
        // Set User Label Visible
        userNameLabel.setVisible(true);
        isAdminLabel.setVisible(true);
        dateRegisteredLabel.setVisible(true);

        // Set User Values Visible
        userNameValueLabel.setVisible(true);
        isAdminValueLabel.setVisible(true);
        dateRegisteredValueLabel.setVisible(true);

        // Set User values
        idValueLabel.setText(String.valueOf(user.getUserID()));
        userNameValueLabel.setText(user.getUserName());
        isAdminValueLabel.setText(String.valueOf(user.getIsAdmin()));
        dateRegisteredValueLabel.setText(String.valueOf(user.getDateRegistered()));
    }
}
