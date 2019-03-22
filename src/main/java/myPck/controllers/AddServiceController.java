package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class AddServiceController {

    //instancja kontrolera zewnętrzenego okna (rodzica)
    private MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    private Button addNewCustomerButton;

    @FXML
    private ListView<?> customersListView;

    @FXML
    private Button addNewCarButton;

    @FXML
    private ListView<?> carsListView;

    @FXML
    private Button saveServiceButton;

    @FXML
    private Button cancelButton;

    @FXML
    void addNewCar(ActionEvent event) {

    }

    @FXML
    void addNewCustomer(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void saveService(ActionEvent event) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {

    }
}
