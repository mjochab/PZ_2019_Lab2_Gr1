package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import myPck.modelsFx.ServiceItemFx;

import java.io.IOException;

public class ServiceReportController {

    @FXML
    public Button backToServiceDetails;
    public Button saveReportButton;
    public TableView<ServiceItemFx> servicesTableView;
    public Button addServiceItemButton;
    public TextField priceInput;
    public TextField serviceNameInput;

    @FXML
    public TableColumn<ServiceItemFx, String> serviceNameColumn;
    @FXML
    public TableColumn<ServiceItemFx, Integer> priceColumn;

    /**
     * Lista zawierająca wykonane usługi
     */
    private ObservableList<ServiceItemFx> servicesList;

    /**
     * Instancja klasy mainStackPaneController
     */
    private MainStackPaneController mainStackPaneController;

    /**
     * Funkcja ustawiająca główny kontroller
     *
     * @param mainStackPaneController
     */
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

    /**
     * Metoda wraca do głównego okna aplikacji.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void backToServiceDetails(ActionEvent actionEvent) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    /**
     * Metoda dodaje raport.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void addReport(ActionEvent actionEvent) throws IOException {
        mainStackPaneController.loadMainWindow();
    }

    /**
     * Metoda dodaje element do listy wykonanych usług.
     *
     * @param actionEvent
     */
    public void addServiceItem(ActionEvent actionEvent) {
        ServiceItemFx item = new ServiceItemFx("Serwis", 25);
        servicesList.add(item);
    }

    @FXML
    void initialize() {
        servicesList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesList);

//        serviceNameColumn.setCellValueFactory(cellData-> cellData.getValue().serviceNameProperty());
//        priceColumn.setCellValueFactory(cellData-> cellData.getValue().priceProperty()));
    }
}
