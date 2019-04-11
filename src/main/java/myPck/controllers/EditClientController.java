package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import myPck.database.models.Client;

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
    private Button resetButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {


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
