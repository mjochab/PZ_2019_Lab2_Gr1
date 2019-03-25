package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class ProfileController {


    @FXML
    private StackPane stackPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField pass1Field;

    @FXML
    private PasswordField pass2Field;

    @FXML
    private Button saveButton;

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
