package myPck.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCarController {
    @FXML
    Button button;
    TextField TextField1;
    TextField TextField2;
    TextField TextField3;
    TextField TextField4;

    public AddCarController() {

    }

    @FXML
    void initialize() {
        button.setText("ADD");
    }

    @FXML
    public void onActionButton() {
        System.out.println("Dodano Samochód");
    }

    @FXML
    public void OnActionTextTextField1() {
        TextField1.getOnAction();

    }

    @FXML
    public void OnActionTextTextField2() {
        TextField1.getOnAction();

    }

    @FXML
    public void OnActionTextTextField3() {
        TextField1.getOnAction();

    }

    @FXML
    public void OnActionTextTextField4() {
        TextField1.getOnAction();
    }
        @FXML
        void OnInputMetodTextChanged() {
            TextField1.appendText("");
            TextField2.appendText("");
            TextField3.appendText("");
            TextField4.appendText("");

        }
    }

