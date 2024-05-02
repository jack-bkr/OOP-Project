package mechanicStock;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class MainApp extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Main.css").toExternalForm());

        primaryStage.setTitle("Mechanic Stock Management System");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
