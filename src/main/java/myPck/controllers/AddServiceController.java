package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.database.models.Service;
import myPck.modelsFx.CarFx;
import myPck.modelsFx.ClientFx;
import myPck.modelsFx.ServiceFx;
import myPck.services.CarService;
import myPck.services.ClientService;
import myPck.services.ServiceService;

import java.io.IOException;
import java.util.List;

public class AddServiceController extends Controller{

    private List<Car> carList;
    private List<Client> clientList;
    private ObservableList<CarFx> carFxList;
    private ObservableList<ClientFx> clientFxList;
    private CarService carService;
    private ClientService clientService;

    public AddServiceController(){
        carService = new CarService();
        clientService = new ClientService();
    }

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private ListView<ClientFx> customersListView;

    @FXML
    private Button addNewCarButton;

    @FXML
    private ListView<CarFx> carsListView;

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
        //String car = carsListView.getSelectionModel().getSelectedItem();
        //String customer= customersListView.getSelectionModel().getSelectedItem();
        //String status = "No allocated";
        //ServiceFx newServiece = new ServiceFx(car, customer, status);

        System.out.println("Zapisuje zlecenie");
        //System.out.println(newServiece.toString());
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {
        setUpCarList();
        setUpClientList();
        loadCars();
        appendCarToCarFx();
        loadClient();
        appendClientToClientFx();

    }
    private void setUpCarList() {
        carFxList = FXCollections.observableArrayList();
        carsListView.setItems(this.carFxList);
    }
    private void setUpClientList() {
        clientFxList = FXCollections.observableArrayList();
        customersListView.setItems(this.clientFxList);
    }
    public void loadCars() {
        this.carList = carService.findAll();
    }
    public void loadClient() {
        clientList = clientService.findAll();
    }
    public void appendCarToCarFx() {
        if (!carList.isEmpty()) {
            for (Car car : carList) {
                CarFx carFx = new CarFx(car.toString());
                carFxList.add(carFx);
            }
        }
    }
    public void appendClientToClientFx() {
        if (!clientList.isEmpty()) {
            for (Client client : clientList) {
                ClientFx clientFx = new ClientFx(client.toString());
                clientFxList.add(clientFx);
            }
        }
    }
}
