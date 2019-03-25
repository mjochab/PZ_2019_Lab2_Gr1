package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogPanelController {

    /**
     * Instancja kontrolera zewnętrzenego okna (rodzica)
     */
    private MainStackPaneController mainStackPaneController;

    /**
     * Ustawia kontroler
     * @param mainStackPaneController
     */
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    @FXML
    void login(ActionEvent event) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {


    }
}
