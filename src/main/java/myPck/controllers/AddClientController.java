package myPck.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AddClientController {

    //instancja kontrolera zewnętrzenego okna (rodzica)
    private MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField nip;

    @FXML
    private TextField address;

    @FXML
    private Button add1;

    @FXML
    void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        //przekazanie kontrolera (głównego okna) do okienka serviceDetails
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        //ustawienie okna serviceDetails
        mainStackPaneController.setScreen(stackPane);
    }

    @FXML
    void initialize() {
        assert fname != null : "fx:id=\"fname\" was not injected: check your FXML file 'AddClientPanel.fxml'.";
        assert lname != null : "fx:id=\"lname\" was not injected: check your FXML file 'AddClientPanel.fxml'.";
        assert nip != null : "fx:id=\"nip\" was not injected: check your FXML file 'AddClientPanel.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'AddClientPanel.fxml'.";
        assert add1 != null : "fx:id=\"add1\" was not injected: check your FXML file 'AddClientPanel.fxml'.";

    }
}
