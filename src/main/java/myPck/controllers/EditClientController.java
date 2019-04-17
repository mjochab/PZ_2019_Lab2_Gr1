package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.database.models.Client;
import myPck.modelsFx.ClientFx;
import myPck.services.ClientService;

import java.io.IOException;

public class EditClientController extends Controller{

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
        convertTextFieldToNumberField(NIPField);
        clientService = new ClientService();
    }
    private Client client;
    private ClientFx clientFx;
    private ClientService clientService;

    public void setClient(Client client) {
        this.client = client;
        this.clientFx = convertClientToClientFx(client);
        setFieldsBinding();
    }
    private ClientFx convertClientToClientFx(Client client){
        return new ClientFx(client.getFirstName(),client.getLastName(),client.getNipNumber(),client.getAddress());
    }
    private void setFieldsBinding(){
        firstNameField.textProperty().bindBidirectional(clientFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(clientFx.lastNameProperty());
        NIPField.textProperty().bindBidirectional(clientFx.NIP_numberProperty());
        addressField.textProperty().bindBidirectional(clientFx.addressProperty());
    }
    @FXML
    void save(ActionEvent event){
        /** zmiana parametrów klienta na nowe */
        client.setFirstName(clientFx.getFirstName());
        client.setLastName(clientFx.getLastName());
        client.setAddress(clientFx.getAddress());
        /** zamiana NIP na liczbę */
        int nip = Integer.parseInt(clientFx.getNIP_number());
        client.setNipNumber(nip);
        /** zapis do bazy danych */
        clientService.update(client);
        /** wywołanie przycisku powrotu */
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
