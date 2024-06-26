package mechanicStock.controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
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

import java.util.ArrayList;

public class ViewController {
    @FXML Label welcomeLabel;
    @SuppressWarnings("rawtypes")
    @FXML TableView viewTable;
    @FXML TextField viewSearchField;
    @FXML Button AddButton;

    User user;
    boolean isAdmin;
    int selectedID;
    String tableType;

    public void recieveSender(User User, String table) { //recieves the user and table type
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
        tableType = table;
        loadTable();

        if (!isAdmin) {
            AddButton.setDisable(true);
        }

        Platform.runLater(() -> {  //refreshes the table when the window is focused
            
            welcomeLabel.getScene().getWindow().focusedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
                {
                    if (onShown)
                    {
                        loadTable();
                    }
                }
            });
        });
    }

    public void loadTable() { //loads the table
        viewTable.getColumns().clear();

        if (tableType.equals("Vehicles")) {
            recieveVehicles(Vehicle.getAllVehicles());
        } else if (tableType.equals("Products")) {
            recieveProducts(Product.getAllProducts());
        } else if (tableType.equals("Users")) {
            recieveUsers(User.getAllUsers());
        }
    }

    @SuppressWarnings("unchecked")
    public void recieveVehicles(ArrayList<Vehicle> vehicles) { //recieves the vehicles
        TableColumn<Vehicle, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<Vehicle, String> makeColumn = new TableColumn<>("Make");
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));

        viewTable.getColumns().addAll(idColumn, makeColumn, modelColumn); //adds the columns to the table

        ObservableList<Vehicle> data = FXCollections.observableArrayList();

        for (Vehicle vehicle : vehicles) { //adds the vehicles to the table
            data.add(vehicle);
        }

        viewTable.setItems(data);

        viewTable.setRowFactory(tv -> { //double click to view vehicle info
            TableRow<Vehicle> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Vehicle vehicle = row.getItem();
                    vehicle.loadInfo(user);
                }
            });
            return row;
        });

        // Searching functionality.

        FilteredList<Vehicle> filteredData = new FilteredList<Vehicle>(data, b -> true);
		
		// Set the filter Predicate whenever the filter changes.
		viewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(vehicle -> {
				// If filter text is empty, display all vehicles.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare make and model of every vehicle with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (vehicle.getVehicleMake().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches make.
				} else if (vehicle.getVehicleModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches model.
				} else return false; // Does not match.
			});
		});
		
		// Wrap the FilteredList in a SortedList. 
		SortedList<Vehicle> sortedData = new SortedList<Vehicle>(filteredData);
		
		// Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(viewTable.comparatorProperty());
		
		// Add sorted (and filtered) data to the viewTable.
		viewTable.setItems(sortedData);
    }

    @SuppressWarnings("unchecked")
    public void recieveProducts(ArrayList<Product> products) { //recieves the products
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));

        viewTable.getColumns().addAll(idColumn, nameColumn, descriptionColumn); //adds the columns to the table

        ObservableList<Product> data = FXCollections.observableArrayList();

        for (Product product : products) { //adds the products to the table
            data.add(product);
        }

        viewTable.setItems(data);

        viewTable.setRowFactory(tv -> { //double click to view product info
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product product = row.getItem();
                    product.loadInfo(user);
                }
            });
            return row;
        });

        // Search functionality.

        FilteredList<Product> filteredData = new FilteredList<Product>(data, b -> true);
		
		// Set the filter Predicate whenever the filter changes.
		viewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all products.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare name and description of every product with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (product.getProductName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches name.
				} else if (product.getProductDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches description.
				} else return false; // Does not match.
			});
		});
		
		// Wrap the FilteredList in a SortedList. 
		SortedList<Product> sortedData = new SortedList<Product>(filteredData);
		
		// Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(viewTable.comparatorProperty());
		
		// Add sorted (and filtered) data to the viewTable.
		viewTable.setItems(sortedData);
    }

    @SuppressWarnings("unchecked")
    public void recieveUsers(ArrayList<User> users) { //recieves the users
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, Boolean> adminColumn = new TableColumn<>("Admin");
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        TableColumn<User, Boolean> approvedColumn = new TableColumn<>("Approved");
        approvedColumn.setCellValueFactory(new PropertyValueFactory<>("adminApproved"));

        TableColumn<User, String> dateColumn = new TableColumn<>("Date Registered");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        viewTable.getColumns().addAll(idColumn, nameColumn, adminColumn, approvedColumn, dateColumn); //adds the columns to the table

        ObservableList<User> data = FXCollections.observableArrayList();

        for (User user : users) { //adds the users to the table
            data.add(user);
        }

        viewTable.setItems(data);

        viewTable.setRowFactory(tv -> { //double click to view user info
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User user = row.getItem();
                    user.loadInfo(this.user);
                }
            });
            return row;
        });

        // Search functionality.

        FilteredList<User> filteredData = new FilteredList<User>(data, b -> true);
		
		// Set the filter Predicate whenever the filter changes.
		viewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare username of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches username.
				} else return false; // Does not match.
			});
		});
		
		// Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<User>(filteredData);
		
		// Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(viewTable.comparatorProperty());
		
		// Add sorted (and filtered) data to the viewTable.
		viewTable.setItems(sortedData);
    }

    @FXML 
    protected void handleAddButton(ActionEvent event) { //handles the add button
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            controller.recieveTable(this.tableType);

            Scene changeScene = new Scene(root, 325, 400);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}