package myPck.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import myPck.database.models.Service;
import myPck.database.models.User;
import myPck.modelsFx.ServiceFx;
import myPck.services.ServiceService;
import myPck.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    public Button assignButton;
    private List<Service> servicesList;
    private ServiceService serviceService;
    private ObservableList<ServiceFx> servicesFxList;
    private UserService userService;

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
    private ComboBox<String> mechanicComboBox;

    private Service selectedService;

    public ManagerController() {
        this.serviceService = new ServiceService();
        this.userService = new UserService();
    }

    @FXML
    void initialize() {
        this.setUpColumns();
        this.setUpServiceList();
        this.loadServices();
        this.loadMechanics();
        this.appendServicesToServicesFx();
    }

    private void setUpColumns() {
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
    }

    private void setUpServiceList() {
        servicesFxList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesFxList);
    }

    public void loadServices() {
        servicesList = serviceService.findNotAllocated();
    }

    public void appendServicesToServicesFx() {
        if (!servicesList.isEmpty()) {
            servicesFxList.clear();
            for (Service service : servicesList) {
                ServiceFx serviceFx = new ServiceFx(service.getCar(), service.getClient(), service.getStatus());
                servicesFxList.add(serviceFx);
            }
        }
    }

    public void loadMechanics() {
        List<User> mechanics = this.userService.findAllByRole("M");
        List<String> mechanicsStringList = new ArrayList<>();

        for(User user: mechanics) {
            mechanicsStringList.add(user.getLogin());
        }

        ObservableList<String> mechanicsCombo = FXCollections.observableList(mechanicsStringList);
        mechanicComboBox.setItems(mechanicsCombo);
    }

    public void assignMechanic(ActionEvent actionEvent) {
        int id = servicesTableView.getSelectionModel().getSelectedIndex();
        this.selectedService = servicesList.get(id);
        String selectedMechanicString = mechanicComboBox.getValue();
        User selectedMechanic = userService.findByLogin(selectedMechanicString);
        this.selectedService.setMechanic(selectedMechanic);
        this.selectedService.setStatus("Waiting for service");
        serviceService.update(this.selectedService);
        servicesFxList.remove(id);
    }
}
