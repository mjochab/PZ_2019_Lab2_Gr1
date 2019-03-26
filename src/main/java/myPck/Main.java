package myPck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import myPck.database.dbutils.DbManager;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainStackPane.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Service station");
        primaryStage.show();

        DbManager.initDatabase();

    }
}
