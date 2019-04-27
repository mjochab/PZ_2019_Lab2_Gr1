package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.services.CarService;
import myPck.services.ClientService;

import java.io.IOException;
import java.util.List;

public class AddServiceController extends Controller {

    private List<Car> carList;
    private List<Client> clientList;
    private ObservableList<String> carNameList;
    private ObservableList<String> clientNameList;
    private CarService carService;
    private ClientService clientService;
    private Object ArrayIndex;

    public AddServiceController() {
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
    private MenuItem editCarMenuItem;
    @FXML
    private  MenuItem deleteCarMenuItem;
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
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddEditCar.fxml"));
            Pane pane = loader.load();
            AddEditCarController addEditCarController = loader.getController();
            addEditCarController.setMainStackPaneController(mainStackPaneController);
            mainStackPaneController.setScreen(pane);
        }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        System.out.println("Anuluje tworzenie zlecenia");
        mainStackPaneController.loadMainWindow();
    }




    @FXML
    void saveService(ActionEvent event) throws IOException {
       // String car = carsListView.getSelectionModel().getSelectedItem();
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
        loadCar();
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
        if (!clientNameList.isEmpty()) {
            /** pobranie id wybranego elemntu */
            int id = customersListView.getSelectionModel().getSelectedIndex();
            /** zaznaczony klient */
            Client selected = clientList.get(id);
            /** ładowanie widou EditClient */
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddEditClient.fxml"));
            Pane pane = loader.load();
            AddEditClientController addEditClientController = loader.getController();
            addEditClientController.setMainStackPaneController(mainStackPaneController);
            mainStackPaneController.setScreen(pane);
            /** wysłanie zaznaczonego klienta do widoku EditClient */
            addEditClientController.setClient(selected);
        }
    }
    @FXML
    void editCar(ActionEvent event) throws IOException {
        if (!carNameList.isEmpty()) {
            /** pobranie id wybranego elemntu */
            int id = carsListView.getSelectionModel().getSelectedIndex();
            /** zaznaczony klient */
            Car selected = carList.get(id);
            /** ładowanie widou EditClient */
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddEditCar.fxml"));
            Pane pane = loader.load();
            AddEditCarController addEditCarController = loader.getController();
            addEditCarController.setMainStackPaneController(mainStackPaneController);
            mainStackPaneController.setScreen(pane);
            /** wysłanie zaznaczonego klienta do widoku EditClient */
            addEditCarController.setCar(selected);
        }
    }
    @FXML
    void addNewClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddEditClient.fxml"));
        Pane pane = loader.load();
        AddEditClientController addEditClientController = loader.getController();
        addEditClientController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(pane);
    }

    @FXML
    void deleteClient(ActionEvent event) {
        if (!clientNameList.isEmpty()) {
            int id = customersListView.getSelectionModel().getSelectedIndex();
            Client selected = clientList.get(id);

            boolean isDelete = clientService.delete(selected.getId());

            if (isDelete) {
                System.out.println("Usunięto");
                clientList.clear();
                loadClient();
                clientNameList.clear();
                appendClientToClientFx();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Can not be deleted");
                alert.setContentText("This client is associated witch a service.");

                alert.showAndWait();
            }
        }
    }
    @FXML
    void deleteCar (ActionEvent event) {
        if (!carNameList.isEmpty()){
            int id = carsListView.getSelectionModel().getSelectedIndex();
            Car selected = carList.get(id);

            boolean isDelete = carService.delete(selected.getId());

            if (isDelete){
                System.out.println("Usunięto");
                carList.clear();
                loadCar();
                carNameList.clear();
                appendCarToCarFx();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Can not be deleted");
                alert.setContentText("This car is associated witch a service.");

                alert.showAndWait();
            }
        }
    }

    private void loadCar() {
        carList = carService.findAll();
    }
}
