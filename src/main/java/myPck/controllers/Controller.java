package myPck.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public abstract class Controller {
    //instancja kontrolera zewnętrzenego okna (rodzica)
    protected MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

}
