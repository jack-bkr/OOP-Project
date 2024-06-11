package mechanicStock.Controllers;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mechanicStock.Classes.*;
import java.util.ArrayList;

import com.google.common.collect.Table;

public class ViewController {
    @FXML
    Label welcomeLabel;
    @FXML
    TableView table;

    User user;
    boolean isAdmin;
    int selectedID;

    public void recieveSender(User User) {
        user = User;
        welcomeLabel.setText("Welcome, " + user.getUserName());
        isAdmin = user.getIsAdmin();
    }

    public void recieveVehicles(ArrayList<Vehicle> vehicles) {
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
    }

    public void recieveProducts(ArrayList<Product> products) {
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
    }

    public void recieveUsers(ArrayList<User> users) {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword"));

        TableColumn<User, Boolean> adminColumn = new TableColumn<>("Admin");
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        TableColumn<User, String> dateColumn = new TableColumn<>("Date Registered");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));

        table.getColumns().addAll(idColumn, nameColumn, passwordColumn, adminColumn, dateColumn);

        ObservableList<User> data = FXCollections.observableArrayList();

        for (User user : users) {
            data.add(user);
        }

        table.setItems(data);
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