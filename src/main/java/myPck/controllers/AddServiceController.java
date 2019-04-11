package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.services.CarService;
import myPck.services.ClientService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class AddServiceController extends Controller{

    private List<Car> carList;
    private List<Client> clientList;
    private ObservableList<String> carNameList;
    private ObservableList<String> clientNameList;
    private CarService carService;
    private ClientService clientService;

    public AddServiceController(){
        carService = new CarService();
        clientService = new ClientService();
    }
    @FXML
    private ContextMenu clientContextMenu;

    @FXML
    private MenuItem editClientMenuItem;

    @FXML
    private MenuItem deleteClientMenuItem;

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
        carNameList = FXCollections.observableArrayList();
        carsListView.setItems(this.carNameList);
    }
    private void setUpClientList() {
        clientNameList = FXCollections.observableArrayList();
        customersListView.setItems(this.clientNameList);
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
                carNameList.add(car.toString());
            }
        }
    }
    public void appendClientToClientFx() {
        if (!clientList.isEmpty()) {
            for (Client client : clientList) {
                clientNameList.add(client.toString());
            }
        }
    }
    @FXML
    void editClient(ActionEvent event) throws IOException {
        System.out.println("Edit");
        int id =customersListView.getSelectionModel().getSelectedIndex();

        Client selected = clientList.get(id);
        System.out.println(selected.getFirstName());

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/EditClient.fxml"));
        Pane pane = loader.load();
        EditClientController editClientController = loader.getController();
        editClientController.setMainStackPaneController(mainStackPaneController);

        mainStackPaneController.setScreen(pane);

    }
    @FXML
    void deleteClient(ActionEvent event){
          System.out.println("Delete");
//        int id =customersListView.getSelectionModel().getSelectedIndex();
//        Client selected = clientList.get(id);
//        clientService.delete(selected.getId());
//        clientList.remove(id);
//        clientNameList.remove(id);
    }


}
