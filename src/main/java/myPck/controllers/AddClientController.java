package myPck.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.database.models.Client;
import myPck.modelsFx.ClientFx;
import myPck.services.ClientService;

public class AddClientController extends Controller {


    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField NIPNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    void add(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String nipNumber = NIPNumberField.getText();
        String address = addressField.getText();
        /** zamiana NIP na liczbę */
        int nip = Integer.parseInt(nipNumber);
        this.client = new Client(firstName,lastName,nip,address);
        clientService.persist(this.client);
        /** wywołanie przycisku powrotu */
        backButton.fire();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(stackPane);
    }

    @FXML
    void initialize() {
        clientService = new ClientService();
    }
    private Client client;
    private ClientFx clientFx;
    private ClientService clientService;


}
