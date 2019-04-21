package myPck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import myPck.database.utils.DbManager;

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

        /** Instancja DbManagera potrzebna to uzupełnienia bazy danymi.**/
        DbManager dbManager = new DbManager();
        /**Uzupełnienie bazy przykładowymi danymi */
//        dbManager.addSampleData();

    }
}
