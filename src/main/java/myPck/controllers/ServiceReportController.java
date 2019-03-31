package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import myPck.modelsFx.ServiceItem;

import java.io.IOException;

public class ServiceReportController extends Controller{

    @FXML
    public Button backToServiceDetails;
    public Button saveReportButton;
    public TableView<ServiceItem> servicesTableView;
    public Button addServiceItemButton;
    public TextField priceInput;
    public TextField serviceNameInput;

    @FXML
    public TableColumn<ServiceItem, String> serviceNameColumn;
    @FXML
    public TableColumn<ServiceItem, String> priceColumn;

    /**
     * Lista zawierająca wykonane usługi
     */
    private ObservableList<ServiceItem> servicesList;


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
        ServiceItem item = new ServiceItem("Serwis", "25");
        servicesList.add(item);
    }

    @FXML
    void initialize() {
        servicesList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesList);

        serviceNameColumn.setCellValueFactory(cellData-> cellData.getValue().serviceNameProperty());
        priceColumn.setCellValueFactory(cellData-> cellData.getValue().priceProperty());
    }
}
