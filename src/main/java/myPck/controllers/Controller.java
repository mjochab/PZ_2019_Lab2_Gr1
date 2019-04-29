package myPck.controllers;

public abstract class Controller {
    //instancja kontrolera zewnętrzenego okna (rodzica)
    protected MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

}
