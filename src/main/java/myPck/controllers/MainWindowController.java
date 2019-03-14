package myPck.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainWindowController {

    @FXML
    private TableView<?> servicesTableView;

    @FXML
    private TableColumn<?, ?> carColumn;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Button addNewServiceButton;

    @FXML
    private Button invoicePDFButton;

    @FXML
    private Button showDetailsButton;

    @FXML
    private Tab profileTab;

    @FXML
    private Tab adminPanelTab;

    @FXML
    private Tab tasksTab;

    @FXML
    void addServicesTest(ActionEvent event) {

    }

    @FXML
    void invoicePDFTest(ActionEvent event) {

    }

    @FXML
    void showDetailsTest(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert servicesTableView != null : "fx:id=\"servicesTableView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert carColumn != null : "fx:id=\"carColumn\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert clientColumn != null : "fx:id=\"clientColumn\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert addNewServiceButton != null : "fx:id=\"addNewServiceButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert invoicePDFButton != null : "fx:id=\"invoicePDFButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert showDetailsButton != null : "fx:id=\"showDetailsButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert profileTab != null : "fx:id=\"profileTab\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert adminPanelTab != null : "fx:id=\"adminPanelTab\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert tasksTab != null : "fx:id=\"tasksTab\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}
