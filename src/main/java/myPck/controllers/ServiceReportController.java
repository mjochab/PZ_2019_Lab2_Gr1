package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import myPck.database.models.Service;
import myPck.database.models.ServicePart;
import myPck.database.models.ServiceReport;
import myPck.modelsFx.ServicePartFx;
import myPck.services.ServicePartService;
import myPck.services.ServiceReportService;

import java.io.IOException;
import java.util.List;

public class ServiceReportController extends Controller{

    @FXML
    public TableView<ServicePartFx> servicesTableView;
    public Button backToServiceDetails;
    public Button saveReportButton;
    public Button addServiceItemButton;
    public Button editReportButton;
    public TextField priceInput;
    public TextField serviceNameInput;

    @FXML
    public TableColumn<ServicePartFx, String> serviceNameColumn;
    @FXML
    public TableColumn<ServicePartFx, String> priceColumn;
    public TextArea reportTextField;
    public Label errorLabel;

    /**
     * Lista zawierająca wykonane usługi
     */
    private ObservableList<ServicePartFx> servicesList;

    private Service service;

    private boolean editMode = false;

    ServiceReportService serviceReportService = new ServiceReportService();
    ServicePartService servicePartService = new ServicePartService();

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
            ServiceReport serviceReport = new ServiceReport();
            errorLabel.setVisible(false);

            /** Zapis do bazy */
            serviceReport.setDescription(reportTextField.getText());
            serviceReport.setService(this.service);
            serviceReportService.persist(serviceReport);

            if (!servicesList.isEmpty()) {
                this.saveServiceParts();
            }

            mainStackPaneController.loadMainWindow(); // Przekierowane do menu glownego
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText("Enter report description");
        }
    }

    public void editReport(ActionEvent actionEvent) throws IOException {
        if (!reportTextField.getText().isEmpty()) {
            errorLabel.setVisible(false);

            /** Zapis do bazy */
            this.service.getServiceReport().setDescription(reportTextField.getText());
            this.service.getServiceReport().setService(this.service);
            this.serviceReportService.update(this.service.getServiceReport());
            this.deleteServiceParts();

            if (!servicesList.isEmpty()) {
                this.saveServiceParts();
            }

            mainStackPaneController.loadMainWindow(); // Przekierowane do menu glownego
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText("Enter report description");
        }
    }
    private void deleteServiceParts() {
        List<ServicePart> serviceParts = service.getServiceParts();
        for (ServicePart servicePart : serviceParts) {
            this.servicePartService.delete(servicePart.getId());
        }
    }

    private void saveServiceParts() {
        for (ServicePartFx servicesListItem : servicesList) {
            ServicePart servicePart = new ServicePart();
            servicePart.setName(servicesListItem.serviceNameProperty().getValue());
            Double price = Double.parseDouble(servicesListItem.priceProperty().getValue());
            servicePart.setPrice(price);
            servicePart.setService(this.service);
            this.servicePartService.persist(servicePart);
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
        ServicePartFx item = new ServicePartFx(serviceNameInput.getText(), priceInput.getText());
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

    /**
     * Metoda ustawia serwis
     * @param service
     */
    public void setService(Service service) {
        this.service = service;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void setUpWindow() {
        if (editMode) {
            editReportButton.setVisible(true);
            saveReportButton.setVisible(false);
            reportTextField.setText(service.getServiceReport().getDescription());
            if (service.getServiceParts() != null) {
                for (ServicePart part: service.getServiceParts()) {
                    ServicePartFx item = new ServicePartFx(part.getName(), Double.toString(part.getPrice()));
                    servicesList.add(item);
                }
            }
        }
    }

    public void deleteServiceItem(ActionEvent actionEvent) {
        int selectedServiceId = servicesTableView.getSelectionModel().getSelectedIndex();
        servicesList.remove(selectedServiceId);
    }
}
