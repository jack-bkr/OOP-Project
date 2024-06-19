package mechanicStock.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mechanicStock.classes.*;

public class MainAppController {
    private static String[] fullpath = dbController.getPath().split(":");
    private static String path = fullpath[fullpath.length - 1];

    @FXML Label welcomeLabel;
    @FXML TableView<Item> table;
    @FXML TextField searchField;
    @FXML Button AddStockButton;

    User user;
    boolean isAdmin;
    
    public void initialize() {
        @SuppressWarnings("unchecked")
        ObservableList<Item> data = populateTable(table);
        
        table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Item item = row.getItem();
                    item.loadInfo(this.user);
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
                        table.getColumns().clear();
                        populateTable(table);
                    }
                }
            });
        });

        FilteredList<Item> filteredData = new FilteredList<Item>(data, b -> true);
		
		// Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (item.getVehicleName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (item.getProductName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else return false; // Does not match.
			});
		});
		
		// Wrap the FilteredList in a SortedList. 
		SortedList<Item> sortedData = new SortedList<>(filteredData);
		
		// Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
    public void recieveUser(User User) {
        this.user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
        if (!isAdmin) {
            AddStockButton.setDisable(true);
        }
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ObservableList populateTable(TableView<Item> table) {
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

        return data;
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

    @FXML
    protected void handlePrintAllStockButton(ActionEvent event) {
        File file = new File(path + "\\Stock-All.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter writer = new FileWriter(file);

            BufferedWriter buffer = new BufferedWriter(writer);

            ArrayList<Stock> stocks = Stock.getAllStock();

            for (Stock stock : stocks) {
                buffer = stock.writeBuffer(buffer);
            }

            buffer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
