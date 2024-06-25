package mechanicStock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception { //starts the program
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));

        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/Main.css").toExternalForm());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

