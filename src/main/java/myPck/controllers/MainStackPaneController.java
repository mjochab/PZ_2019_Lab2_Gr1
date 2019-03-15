package myPck.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import myPck.Service;

public class MainStackPaneController {


    @FXML
    private StackPane mainStackPane;

    @FXML
    void initialize() throws IOException {
        assert mainStackPane != null : "fx:id=\"mainStackPane\" was not injected: check your FXML file 'MainStackPane.fxml'.";
        //pobranie okna które wyświetli się jako pierwsze po uruchomieniu aplikacji
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainWindow.fxml"));
        StackPane stackPane = loader.load();

        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setMainStackPaneController(this);
        setScreen(stackPane);

    }

    public void setScreen(StackPane stackPane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(stackPane);
    }
}
