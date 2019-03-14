package myPck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainWindow.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Service station");

        primaryStage.show();

    }
}
