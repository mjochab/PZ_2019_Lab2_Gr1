package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.controllers.utils.Validator;
import myPck.database.models.Client;
import myPck.modelsFx.ClientFx;
import myPck.services.ClientService;

import java.io.IOException;

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

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || nipNumber.isEmpty() || nipNumber.length() != 10) {
            System.out.println("Error");
        } else {

            this.client = new Client(firstName, lastName, nipNumber, address);
            clientService.persist(this.client);
            /** wywołanie przycisku powrotu */
            backButton.fire();
        }

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
        Validator.setMaxLengthOfTextField(firstNameField, 200);
        Validator.setMaxLengthOfTextField(lastNameField, 200);
        Validator.setMaxLengthOfTextField(NIPNumberField, 10);
        Validator.setMaxLengthOfTextField(addressField, 50);
        Validator.convertTextFieldToNumberField(NIPNumberField);
        clientService = new ClientService();
    }

    private Client client;
    private ClientFx clientFx;
    private ClientService clientService;

}
