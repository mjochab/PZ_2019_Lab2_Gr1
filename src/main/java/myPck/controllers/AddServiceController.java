package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import myPck.Service;

import java.io.IOException;

public class AddServiceController extends Controller{

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
    void addNewCar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddCarPanel.fxml"));
        Pane pane = loader.load();
        AddCarController addCarController = loader.getController();
        addCarController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(pane);
    }

    @FXML
    void addNewCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddClientPanel.fxml"));
        Pane pane = loader.load();
        AddClientController addClientController = loader.getController();
        addClientController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(pane);
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
