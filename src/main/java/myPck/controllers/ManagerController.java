package myPck.controllers;


import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import myPck.database.models.Service;
import myPck.modelsFx.ServiceFx;
import myPck.services.ServiceService;

public class ManagerController {

    private List<Service> servicesList;
    private ServiceService serviceService;
    private ObservableList<ServiceFx> servicesFxList;

    public ManagerController() {
        this.serviceService = new ServiceService();
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<ServiceFx> servicesTableView;

    @FXML
    private TableColumn<ServiceFx, String> carColumn;

    @FXML
    private TableColumn<ServiceFx, String> clientColumn;

    @FXML
    private TableColumn<ServiceFx, String> mechanicColumn;

    @FXML
    private ComboBox<?> mechanicComboBox;

    @FXML
    void initialize() {
        this.setUpColumns();
        this.setUpServiceList();
        this.loadServices();
        this.appendUsersToUsersFx();

    }
    private void setUpColumns() {
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        mechanicColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());
    }
    private void setUpServiceList() {
        servicesFxList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesFxList);
    }
    public void loadServices() {
        servicesList = serviceService.findAll();
    }
    public void appendUsersToUsersFx() {
        if (!servicesList.isEmpty()) {
            for (Service service : servicesList) {
                ServiceFx serviceFx = new ServiceFx(service.getCar(), service.getClient(), service.getStatus());
                servicesFxList.add(serviceFx);
            }
        }
    }
}
