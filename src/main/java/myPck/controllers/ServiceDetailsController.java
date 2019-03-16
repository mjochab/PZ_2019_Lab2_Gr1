package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ServiceDetailsController {

    private MainStackPaneController mainStackPaneController;

    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    private TextField carTextField;

    @FXML
    private TextField customerTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private TextArea faultDescTextArea;

    @FXML
    private TextArea repaiDescTextArea;

    @FXML
    private Button addRaportButton;

    @FXML
    private Button backButton;

    @FXML
    void addRaport(ActionEvent event) {

    }

    @FXML
    void backToMainWindow(ActionEvent event) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {
        assert carTextField != null : "fx:id=\"carTextField\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert customerTextField != null : "fx:id=\"customerTextField\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert faultDescTextArea != null : "fx:id=\"faultDescTextArea\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert repaiDescTextArea != null : "fx:id=\"repaiDescTextArea\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert addRaportButton != null : "fx:id=\"addRaportButton\" was not injected: check your FXML file 'ServiceDetails.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ServiceDetails.fxml'.";

    }
}
