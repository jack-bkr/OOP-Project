package mechanicStock.controllers;

import javafx.collections.*;
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
    @FXML TableView table;

    User user;
    boolean isAdmin;
    int selectedID;
    String tableType;

    public void recieveSender(User User) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
    }

    @SuppressWarnings("unchecked")
    public void recieveVehicles(ArrayList<Vehicle> vehicles) {
        tableType = "Vehicles";
        
        TableColumn<Vehicle, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<Vehicle, String> makeColumn = new TableColumn<>("Make");
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));

        table.getColumns().addAll(idColumn, makeColumn, modelColumn);

        ObservableList<Vehicle> data = FXCollections.observableArrayList();

        for (Vehicle vehicle : vehicles) {
            data.add(vehicle);
        }

        table.setItems(data);

        table.setRowFactory(tv -> {
            TableRow<Vehicle> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Vehicle vehicle = row.getItem();
                    vehicle.loadInfo();
                }
            });
            return row;
        });
    }

    @SuppressWarnings("unchecked")
    public void recieveProducts(ArrayList<Product> products) {
        tableType = "Products";

        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));

        table.getColumns().addAll(idColumn, nameColumn, descriptionColumn);

        ObservableList<Product> data = FXCollections.observableArrayList();

        for (Product product : products) {
            data.add(product);
        }

        table.setItems(data);

        table.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product product = row.getItem();
                    product.loadInfo();
                }
            });
            return row;
        });
    }

    @SuppressWarnings("unchecked")
    public void recieveUsers(ArrayList<User> users) {
        tableType = "Users";

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, Boolean> adminColumn = new TableColumn<>("Admin");
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        TableColumn<User, String> dateColumn = new TableColumn<>("Date Registered");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        table.getColumns().addAll(idColumn, nameColumn, adminColumn, dateColumn);

        ObservableList<User> data = FXCollections.observableArrayList();

        for (User user : users) {
            data.add(user);
        }

        table.setItems(data);

        table.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User user = row.getItem();
                    user.loadInfo();
                }
            });
            return row;
        });
    }

    @FXML 
    protected void handleAddButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Add.fxml"));
            Parent root = loader.load();

            AddController controller = loader.getController();
            //controller.recieveTable(table);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleEditButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Edit.fxml"));
            Parent root = loader.load();

            EditController controller = loader.getController();
            //controller.recieveTable(table, selectedID);

            Scene changeScene = new Scene(root, 400, 600);
            changeScene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());
            stage.setScene(changeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleDeleteButton(ActionEvent event) {
    }
}