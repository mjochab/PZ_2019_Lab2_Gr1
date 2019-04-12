package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.modelsFx.ClientFx;
import myPck.services.ClientService;

import java.io.IOException;

public class EditClientController extends Controller{

    private Client client;
    private ClientFx clientFx;
    private ClientService clientService;


    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField NIPField;

    @FXML
    private TextField addressField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        clientService = new ClientService();
    }
    public void setData(){
        clientFx = new ClientFx(client.getFirstName(),client.getLastName(),client.getNipNumber(),client.getAddress());
        setBinding();
    }
    public void setBinding(){
        firstNameField.textProperty().bindBidirectional(clientFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(clientFx.lastNameProperty());
        NIPField.textProperty().bindBidirectional(clientFx.NIP_numberProperty());
        addressField.textProperty().bindBidirectional(clientFx.addressProperty());
    }
    @FXML
    void save(ActionEvent event){
        System.out.println("Save");
        client.setFirstName(clientFx.getFirstName());
        client.setLastName(clientFx.getLastName());
        client.setAddress(clientFx.getAddress());
        int nip = Integer.parseInt(client.getNipNumber());
        client.setNipNumber(nip);

        clientService.update(client);

        backButton.fire();

    }
    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        /** przekazanie kontrolera (głównego okna) do okienka AddService */
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        /** Ustawienie okna AddService */
        mainStackPaneController.setScreen(stackPane);

    }
}
