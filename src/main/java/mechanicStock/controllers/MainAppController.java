package mechanicStock.controllers;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mechanicStock.classes.*;

public class MainAppController {
    @FXML
    Label welcomeLabel;
    @FXML TableView<Item> table;

    User user;
    boolean isAdmin;
    
    public void initialize() {
        populateTable(table);
        
        table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Item item = row.getItem();
                    item.loadInfo();
                }
            });
            return row;
        });
        Platform.runLater(() -> {
            welcomeLabel.getScene().getWindow().focusedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
                {
                    if (onShown)
                    {
                    populateTable(table);
                    }
                }
            });
        });
    }
    
    public void recieveUser(User User) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
    }

    
    @SuppressWarnings("unchecked")
    public static void populateTable(TableView<Item> table) {

        table.getItems().clear();
        table.getColumns().clear();

        ArrayList<Item> items = Item.getAllItems();

        TableColumn<Item, Integer> stockIDColumn = new TableColumn<>("stockID");
        stockIDColumn.setCellValueFactory(new PropertyValueFactory<>("stockID"));

        TableColumn<Item, String> vehicleNameColumn = new TableColumn<>("Vehicle Name");
        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));

        TableColumn<Item, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Item, String> productDescColumn = new TableColumn<>("Product Description");
        productDescColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));

        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        TableColumn<Item, Integer> buyPriceColumn = new TableColumn<>("Buy Price");
        buyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));

        TableColumn<Item, Integer> sellPriceColumn = new TableColumn<>("Sell Price");
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

        table.getColumns().addAll(stockIDColumn, vehicleNameColumn, productNameColumn, productDescColumn,
                quantityColumn, buyPriceColumn, sellPriceColumn);

        ObservableList<Item> data = FXCollections.observableArrayList();

        for (Item item : items) {
            data.add(item);
        }

        table.setItems(data);
    }
    
    @FXML 
    protected void handleLogoutButton(ActionEvent event) {
        try { 
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Login.fxml"));
            Parent root = loader.load();

            Scene changeScene = new Scene(root, 300, 275);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    protected void handleViewProductsButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Products");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Products");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleViewVehiclesButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Vehicles");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Vehicles");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handleViewUsersButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/View.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.recieveSender(user, "Users");

            Scene changeScene = new Scene(root, 900, 500);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Users");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void handlePrintAllButton(ActionEvent event) {

    }
    
    @FXML
    protected void handleAddStockButton(ActionEvent event) {
        try { 
            Stage stage = (new Stage());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable("Stock");

            Scene changeScene = new Scene(root, 325, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.setTitle("Add Stock");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
