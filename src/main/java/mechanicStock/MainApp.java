package mechanicStock;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mechanic Stock Management System");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
