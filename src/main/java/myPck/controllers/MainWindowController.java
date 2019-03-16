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

import java.util.ArrayList;

// tym enum potrzebny tylko do testowania widoku
enum Type_of_account{
    M,  //mechanik
    R,  //recepcjonista
    A,  //admin
    K,  //kierownik
};

public class MainWindowController {

    @FXML
    public TableColumn<Service, String> loginColumn;
    @FXML
    public TableColumn<Service, String> firstNameColumn;
    @FXML
    public TableColumn<Service, String> lastNameColumn;
    @FXML
    public TableColumn<Service, String> roleColumn;
    //TU USTAW TYP KONTA !!!!!!!!!!!!!!!!!!!!!!
    Type_of_account ACCOUNT = Type_of_account.A;

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
    public TableView<Service> usersTableView;

    //lista zawierająca zlecenia
    private ObservableList<Service> usersList;

    @FXML
    //ta funkcja w przyszłości będzie otwierać nowe okno (panel dodawanie zlecenia)
    void addServicesTest(ActionEvent event) {
        //przykładowy obiekt
        Service service = new Service("Fiat Multipla RZ746H"+Service.amount, "Jan Kowalski", "Waiting for assigment");
        //dodanie do listy
        servicesList.add(service);

        System.out.println("Dodano do listy nowe zlecenie");
        //zarządzanie widocznością przycisków
        buttonManagment();
    }

    @FXML
    // generowanie faktury
    void invoicePDFTest(ActionEvent event) {
        Service service;
        try{
            service = servicesTableView.getSelectionModel().getSelectedItem();
            System.out.println("Generuje PDF dla:");
            System.out.println(service.getCar());
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
    }

    @FXML
    // ta funkcja w przyszłości ma otwierać okno ze szczegółowymi informacjami o wybranym zleceniu
    void showDetailsTest(ActionEvent event) {
        Service service;
        try{
            service = servicesTableView.getSelectionModel().getSelectedItem();
            System.out.println("Pokazuje detale dla zlecenia z samochodem : ");
            System.out.println(service.getCar());
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
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

        System.out.println("Wersja dla konta: "+ACCOUNT);

        servicesList = FXCollections.observableArrayList();
        usersList = FXCollections.observableArrayList();
        //podpięcie listy do tabelki

        servicesTableView.setItems(this.servicesList);
        usersTableView.setItems(this.usersList);

        //ustawienie wartości które mają się wyświetlać w poszczególnych kolumnach
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        statusColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());

        loginColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        firstNameColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        lastNameColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        roleColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());

        //zarządzanie dostępem przycisków
        buttonManagment();

        //ukrywanie elementów dla kont bez uprawnień
        switch (ACCOUNT){
            case A:
                sampleData();
                tasksTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case K:
                sampleData();
                adminPanelTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case M:
                sampleData();
                tasksTab.setDisable(true);
                adminPanelTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case R:
                adminPanelTab.setDisable(true);
                tasksTab.setDisable(true);
                break;

        }

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

    /**
     * Metoda dodaje nowego użytkownika do listy.
     * @param actionEvent
     */
    public void addUser(ActionEvent actionEvent) {
        Service service = new Service("Marekx", "Kowalski ", "Administrator");
        usersList.add(service);
        System.out.println("Dodano do listy nowego uzytkownika");
    }
}
