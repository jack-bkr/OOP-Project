package mechanicStock;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class Login extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mechanic Stock Management System");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Scene login = new Scene(grid, 300, 275);
        primaryStage.setScene(login);

        // Add Header
        Text loginTitle = new Text("Welcome");
        loginTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(loginTitle, 0, 0, 2, 1);

        // Add Username Label
        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        // Add Username TextField
        TextField userNameTextField = new TextField();
        grid.add(userNameTextField, 1, 1);

        // Add Password Label
        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 2);

        // Add Password TextField
        PasswordField passWordTextField = new PasswordField();
        grid.add(passWordTextField, 1, 2);

        // Add Login Button
        Button loginButton = new Button("Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 1, 3);

        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
