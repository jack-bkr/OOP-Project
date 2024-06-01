package mechanicStock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        User user = getUser(userNameTextField.getText());

        if (user == null || !user.checkPassword(passwordTextField.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username/Password");
            alert.setContentText("The Username/Password you entered is incorrect. Please try again.");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainApp.fxml"));
        Parent root = loader.load();

        MainAppController controller = loader.getController();
        controller.recieveUser(user);

        Scene changeScene = new Scene(root, 900, 500);
        changeScene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());
        stage.setScene(changeScene);
        stage.setTitle("Mechanic Stock Management System");
        stage.show();
    }
    
    public static User getUser(String userName) {
        String dbURL = "jdbc:sqlite::resource:mechanicStockDB.sqlite";
        String query = "SELECT * FROM Users WHERE userName = '" + userName + "';";
        Connection conn = null;
        Statement stmt = null;
        User user = null;
        
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC driver loaded.");

            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"), rs.getBoolean("isAdmin"), rs.getString("dateRegistered"));
            }

            stmt.close();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                conn = null;
            }
        }
        return user;
    }
}
