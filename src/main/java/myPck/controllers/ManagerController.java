package myPck.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<?> servicesTableView;

    @FXML
    private TableColumn<?, ?> carColumn;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> mechanicColumn;

    @FXML
    private ComboBox<?> mechanicComboBox;

    @FXML
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'ManagerPanel.fxml'.";
        assert servicesTableView != null : "fx:id=\"servicesTableView\" was not injected: check your FXML file 'ManagerPanel.fxml'.";
        assert carColumn != null : "fx:id=\"carColumn\" was not injected: check your FXML file 'ManagerPanel.fxml'.";
        assert clientColumn != null : "fx:id=\"clientColumn\" was not injected: check your FXML file 'ManagerPanel.fxml'.";
        assert mechanicColumn != null : "fx:id=\"mechanicColumn\" was not injected: check your FXML file 'ManagerPanel.fxml'.";
        assert mechanicComboBox != null : "fx:id=\"mechanicComboBox\" was not injected: check your FXML file 'ManagerPanel.fxml'.";

    }
}
