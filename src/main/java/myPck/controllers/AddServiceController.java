package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import myPck.database.models.Service;

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
    private ListView<String> customersListView;

    @FXML
    private Button addNewCarButton;

    @FXML
    private ListView<String> carsListView;

    @FXML
    private Button saveServiceButton;

    @FXML
    private Button cancelButton;

    @FXML
    void addNewCar(ActionEvent event) {
        carsListView.getItems().add("Test");
    }

    @FXML
    void addNewCustomer(ActionEvent event) {
        customersListView.getItems().add("Test");
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        System.out.println("Anuluje tworzenie zlecenia");
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void saveService(ActionEvent event) throws IOException {
        String car = carsListView.getSelectionModel().getSelectedItem();
        String customer= customersListView.getSelectionModel().getSelectedItem();
        String status = "No allocated";
        Service newServiece = new Service(car, customer, status);

        System.out.println("Zapisuje zlecenie");
        System.out.println(newServiece.toString());
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {

    }
}
