package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.controllers.utils.Validator;
import myPck.database.models.Client;
import myPck.modelsFx.ClientFx;
import myPck.services.ClientService;

import java.io.IOException;

public class AddEditClientController extends Controller {

    @FXML
    private Label firstNameInfo;
    @FXML
    private Label lastNameInfo;
    @FXML
    private Label NIPnumberInfo;
    @FXML
    private Label addressInfo;
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
        coverInfoLabels();
        Validator.setMaxLengthOfTextField(firstNameField, 200);
        Validator.setMaxLengthOfTextField(lastNameField, 200);
        Validator.setMaxLengthOfTextField(addressField, 50);
        Validator.convertTextFieldToNumberField(NIPField);
        Validator.setMaxLengthOfTextField(NIPField, 10);
        clientService = new ClientService();
    }

    private Client client = null;
    private ClientFx clientFx;
    private ClientService clientService;

    public void setClient(Client client) {
        this.client = client;
        this.clientFx = convertClientToClientFx(client);
        setFieldsBinding();
    }

    private ClientFx convertClientToClientFx(Client client) {
        return new ClientFx(client.getFirstName(), client.getLastName(), client.getNipNumber(), client.getAddress());
    }
    void coverInfoLabels(){
        firstNameInfo.setVisible(false);
        lastNameInfo.setVisible(false);
        addressInfo.setVisible(false);
        NIPnumberInfo.setVisible(false);
    }
    private void setFieldsBinding() {
        firstNameField.textProperty().bindBidirectional(clientFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(clientFx.lastNameProperty());
        NIPField.textProperty().bindBidirectional(clientFx.NIP_numberProperty());
        addressField.textProperty().bindBidirectional(clientFx.addressProperty());
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        if (this.client != null) {
            edit();
        } else {
            this.addNew();
        }
    }

    void edit() {
        /** zmiana parametrów klienta na nowe */
        client.setFirstName(clientFx.getFirstName());
        client.setLastName(clientFx.getLastName());
        client.setAddress(clientFx.getAddress());
        client.setNipNumber(clientFx.getNIP_number());

        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String nipNumber = client.getNipNumber();
        String address = client.getAddress();
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || nipNumber.isEmpty() || nipNumber.length() != 10) {
            coverInfoLabels();
            if(firstName.isEmpty()) firstNameInfo.setVisible(true);
            if(lastName.isEmpty()) lastNameInfo.setVisible(true);
            if(address.isEmpty()) addressInfo.setVisible(true);
            if(nipNumber.isEmpty()) NIPnumberInfo.setVisible(true);
            if(nipNumber.length() !=10) NIPnumberInfo.setVisible(true);
        } else {
            /** zapis do bazy danych */
            clientService.update(client);
            /** wywołanie przycisku powrotu */
            backButton.fire();
        }
    }

    void addNew() throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String nipNumber = NIPField.getText();
        String address = addressField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || nipNumber.isEmpty() || nipNumber.length() != 10) {
            coverInfoLabels();
            if(firstName.isEmpty()) firstNameInfo.setVisible(true);
            if(lastName.isEmpty()) lastNameInfo.setVisible(true);
            if(address.isEmpty()) addressInfo.setVisible(true);
            if(nipNumber.isEmpty()) NIPnumberInfo.setVisible(true);
            if(nipNumber.length() !=10) NIPnumberInfo.setVisible(true);
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
}
