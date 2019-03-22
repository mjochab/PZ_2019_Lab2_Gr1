package myPck.controllers;


import javafx.fxml.FXML;

public class AddServiceController {

    //instancja kontrolera zewnętrzenego okna (rodzica)
    private MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    void initialize() {

    }
}
