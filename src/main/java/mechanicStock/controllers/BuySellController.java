package mechanicStock.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import mechanicStock.classes.Stock;

public class BuySellController {
    private Stock stock;

    // FXML elements
    // Spinners
    @FXML private Spinner<Integer> quantitySpinner;
    
    // Labels
    @FXML private Label titleLabel;
    @FXML private Label value;
    
    // Buttons
    @FXML private Button buySellButton;

    public void recieveStock(Stock stock) {
        this.stock = stock;
        titleLabel.setText(stock.product.getProductName() + " - " + stock.vehicle.getVehicleMake() + " " + stock.vehicle.getVehicleModel());

        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, stock.getStockQuantity()));
    }
    
    @FXML
    protected void handleBuySellButtonAction() {
        if (stock.setQuantity(quantitySpinner.getValue())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Transaction Successful");
            alert.setContentText("Transaction completed successfully, quantity updated.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Transaction Failed");
            alert.setContentText("Transaction failed. Please try again.");
            alert.showAndWait();
        
        }

    }

    @FXML
    protected void handleSpinnerChange() {
        if (quantitySpinner.getValue() < stock.getStockQuantity()) {
            value.setText("+£" + (stock.getSellPrice() * (stock.getStockQuantity() - quantitySpinner.getValue())));
            buySellButton.setText("Sell");
            buySellButton.disableProperty().set(false);
        } else if (quantitySpinner.getValue() > stock.getStockQuantity()){
            value.setText("-£" + (stock.getBuyPrice() * (quantitySpinner.getValue() - stock.getStockQuantity())));
            buySellButton.setText("Buy");
            buySellButton.disableProperty().set(false);
        } else {
            value.setText("£0");
            buySellButton.disableProperty().set(true);
        }
    }
}
