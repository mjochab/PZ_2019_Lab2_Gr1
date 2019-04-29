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
import myPck.database.models.Service;
import myPck.services.CarService;
import myPck.services.ClientService;
import myPck.services.ServiceService;

import java.io.IOException;
import java.util.List;

public class AddServiceController extends Controller {

    private List<Car> carList;
    private List<Client> clientList;
    private ObservableList<String> carNameList;
    private ObservableList<String> clientNameList;
    private CarService carService;
    private ClientService clientService;
    private ServiceService serviceService;
    private Object ArrayIndex;

    public AddServiceController() {
        serviceService = new ServiceService();
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
    private TextArea descTextArea;
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
        /** pobranie id wybranego elemntu */
        int idCustomer = customersListView.getSelectionModel().getSelectedIndex();
        /** zaznaczony klient */
        Client selectedCustomer = clientList.get(idCustomer);

        /** pobranie id wybranego elemntu */
        int idCar = carsListView.getSelectionModel().getSelectedIndex();
        /** zaznaczony klient */
        Car selectedCar = carList.get(idCar);


        String status = "Not allocated";
        Service newService = new Service(selectedCustomer,selectedCar,status);
        newService.setDescription(descTextArea.getText());
        System.out.println("Zapisuje zlecenie");
        serviceService.persist(newService);
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
                System.out.println("Nie usunięto");
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
                System.out.println("Nie usunięto");
            }
        }
    }

    private void loadCar() {
        carList = carService.findAll();
    }
}
