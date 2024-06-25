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
    protected void handleBuySellButtonAction() { //handles the buy/sell button
        if (stock.setQuantity(quantitySpinner.getValue())) { //updates the quantity of the stock 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Transaction Successful");
            alert.setContentText("Transaction completed successfully, quantity updated.");
            alert.showAndWait();
        } else { //if the transaction fails, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Transaction Failed");
            alert.setContentText("Transaction failed. Please try again.");
            alert.showAndWait();
        
        }

    }

    @FXML
    protected void handleSpinnerChange() { //handles the spinner change
        if (quantitySpinner.getValue() < stock.getStockQuantity()) { //if the quantity is less than the stock quantity, calculate the value of the stock and set button to sell
            value.setText("+£" + (stock.getSellPrice() * (stock.getStockQuantity() - quantitySpinner.getValue())));
            buySellButton.setText("Sell");
            buySellButton.disableProperty().set(false);
        } else if (quantitySpinner.getValue() > stock.getStockQuantity()){ //if the quantity is more than the stock quantity, calculate the value of the stock and set button to buy
            value.setText("-£" + (stock.getBuyPrice() * (quantitySpinner.getValue() - stock.getStockQuantity())));
            buySellButton.setText("Buy");
            buySellButton.disableProperty().set(false);
        } else { //if the quantity is equal to the stock quantity, set the value to 0 and disable the button
            value.setText("£0");
            buySellButton.disableProperty().set(true);
        }
    }
}
