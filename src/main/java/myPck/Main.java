package myPck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/border.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Moja biblioteczka");

        primaryStage.show();

    }
}
