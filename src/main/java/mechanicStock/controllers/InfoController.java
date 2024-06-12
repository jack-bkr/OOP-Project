package mechanicStock.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import mechanicStock.classes.*;

public class InfoController {
    // Attributes
    private Stock stock;

    // FXML Attributes
    @FXML
    private Label idValueLabel;

    // Images
    @FXML
    private Label imageLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label secondaryImageLabel;
    @FXML
    private ImageView secondaryImageView;
    
    // Buttons
    @FXML
    private Button productInfoButton;
    @FXML
    private Button vehicleInfoButton;

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
        this.stock = stock;

        // Set Product and Vehicle Info Buttons visible
        productInfoButton.setVisible(true);
        vehicleInfoButton.setVisible(true);

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
        // Set Images Visible
        imageLabel.setVisible(true);
        imageView.setVisible(true);

        // Set Image values
        imageLabel.setText("Vehicle Image");
        imageView.setImage(new Image("/img/vehicle/" + vehicle.getVehicleID() + ".png", 0, 100, true, false));

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

    @FXML
    protected void handleProductInfoButton(ActionEvent event) {
        stock.product.loadInfo();
    }

    @FXML
    protected void handleVehicleInfoButton(ActionEvent event) {
        stock.vehicle.loadInfo();
    }
}
