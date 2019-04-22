package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import myPck.database.models.ServicePart;
import myPck.database.models.ServiceReport;
import myPck.modelsFx.ServiceItem;
import myPck.services.ServicePartService;
import myPck.services.ServiceReportService;

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
    public TextArea reportTextField;
    public Label errorLabel;

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
        if (!reportTextField.getText().isEmpty()) {
            errorLabel.setVisible(false);

            ServiceReport serviceReport = new ServiceReport();
            ServiceReportService serviceReportService = new ServiceReportService();

            /** Zapis do bazy */
//            serviceReport.setDescription(reportTextField.getText());
//            serviceReportService.persist(serviceReport);

            if (!servicesList.isEmpty()) {
                ServicePartService servicePartService = new ServicePartService();
                for (ServiceItem servicesListItem : servicesList) {
                    ServicePart servicePart = new ServicePart();
                    servicePart.setName(servicesListItem.serviceNameProperty().getValue());
                    Double price = Double.parseDouble(servicesListItem.priceProperty().getValue());
                    servicePart.setPrice(price);
//                    servicePart.setService(); //Ustawić serwis

                    servicePartService.persist(servicePart);
                }
            }

//        mainStackPaneController.loadMainWindow(); // Przekierowane do menu glownego
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText("Enter report description");
        }
    }

    /**
     * Metoda dodaje element do listy wykonanych usług.
     *
     * @param actionEvent
     */
    public boolean addServiceItem(ActionEvent actionEvent) {
        if (serviceNameInput.getText().isEmpty()) {
            errorLabel.setText("Enter service name value");

            return false;
        }

        if (priceInput.getText().isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Enter price value");

            return false;
        }

        try {
            Integer.parseInt(priceInput.getText());
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText("Price has to be a digit");

            return false;
        }

        errorLabel.setVisible(false);

        ServiceItem item = new ServiceItem(serviceNameInput.getText(), priceInput.getText());
        servicesList.add(item);

        serviceNameInput.setText("");
        priceInput.setText("");

        return true;
    }

    @FXML
    void initialize() {
        servicesList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesList);

        serviceNameColumn.setCellValueFactory(cellData-> cellData.getValue().serviceNameProperty());
        priceColumn.setCellValueFactory(cellData-> cellData.getValue().priceProperty());
    }
}
