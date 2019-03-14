package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import myPck.Service;

public class MainWindowController {

    //lista zawierająca zlecenia
    private ObservableList<Service> servicesList;
    @FXML
    //tableka wyświetlająca dane z listy ze zleceniami
    private TableView<Service> servicesTableView;

    @FXML
    //kolumna zawierająca informacje o samochodzie
    private TableColumn<Service, String> carColumn;

    @FXML
    //kolumna zawierająca informacje o klięcie
    private TableColumn<Service, String> clientColumn;

    @FXML
    // kolumna zawierająca informacje o statusie zlecenia
    private TableColumn<Service, String> statusColumn;

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

        servicesList = FXCollections.observableArrayList();
        //podpięcie listy do tabelki
        servicesTableView.setItems(this.servicesList);
        //ustawienie wartości które mają się wyświetlać w poszczególnych kolumnach
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        statusColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());

    }
    //tylko do testów
    void sampleData(){
        //dodanie przykładowego obiektu
        //przykładowy obiekt
        Service service1 = new Service("Jaguar XE", "Konrad Rejman", "Done");
        Service service2 = new Service("Skoda Fabia", "Bartek Kudełka", "in Repair");
        Service service3 = new Service("Opel Astra", "Filip Rebizant", "not allocated");
        Service service4 = new Service("Toyota Auris", "Marek Wojdyła", "not allocated");
        //dodanie do listy
        servicesList.add(service1);
        servicesList.add(service2);
        servicesList.add(service3);
        servicesList.add(service4);
        buttonManagment();
    }
    //funkcja wyłącza dostęp do przycisków recepcjonisty gdy nie ma żadnych zleceń w "bazie danych"
    void buttonManagment(){
        if(servicesList.isEmpty()){
            invoicePDFButton.setDisable(true);
            showDetailsButton.setDisable(true);
        }
        else {
            invoicePDFButton.setDisable(false);
            showDetailsButton.setDisable(false);
        }
    }
}
