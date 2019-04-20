package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import myPck.database.models.Service;
import myPck.modelsFx.ServiceFx;
import myPck.services.ServiceService;

import java.io.IOException;
import java.util.List;

public class MainWindowController extends Controller{

    /** Lista zawierająca zlecenia */
    private ObservableList<ServiceFx> servicesFxList;

    @FXML
    /** Tabela wyświetlająca dane z listy ze zleceniami */
    private TableView<ServiceFx> servicesTableView;

    @FXML
    /** kolumna zawierająca informacje o samochodzie */
    private TableColumn<ServiceFx, String> carColumn;

    @FXML
    /** kolumna zawierająca informacje o kliencie */
    private TableColumn<ServiceFx, String> clientColumn;

    @FXML
    /** kolumna zawierająca informacje o statusie zlecenia */
    private TableColumn<ServiceFx, String> statusColumn;

    /** Przyciski */
    @FXML
    private Button addNewServiceButton;
    @FXML
    private Button invoicePDFButton;
    @FXML
    private Button showDetailsButton;

    /** Zakładki */
    @FXML
    private Tab profileTab;
    @FXML
    private Tab adminPanelTab;
    @FXML
    private Tab tasksTab;

    /** Lista zawierająca zlecenia */
    private List<Service> servicesList;

    /** Serwis Zleceń */
    private ServiceService serviceService;

    /**
     * Konstruktor MainWindowControlle i inicjalizacja Serwisu zleceń
     */
    public MainWindowController() {
        this.serviceService = new ServiceService();
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void addServicesTest(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        /** przekazanie kontrolera (głównego okna) do okienka AddService */
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        /** Ustawienie okna AddService */
        mainStackPaneController.setScreen(stackPane);
    }

    /**
     * generowanie faktury
     * @param event
     */
    @FXML
    void invoicePDFTest(ActionEvent event) {
        ServiceFx service;
        try{
            /** sprawdza czy zaznaczono jakiś element w TableView */
            service = servicesTableView.getSelectionModel().getSelectedItem();
            System.out.println("Generuje PDF dla:");
            System.out.println(service.getCar());
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
    }
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void showDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ServiceDetails.fxml"));
        StackPane stackPane = loader.load();
        Service selected;
        try{
            /** sprawdza czy zaznaczono jakieś zlecenie w TableView */
            int id =servicesTableView.getSelectionModel().getSelectedIndex();
            selected = servicesList.get(id);

            ServiceDetailsController serviceDetailsController = loader.getController();
            /** przekazanie zaznaczonego serwisu */
            serviceDetailsController.setService(selected);
            /** wyswietlenie danych o serwisie */
            serviceDetailsController.setData();

            serviceDetailsController.setMainStackPaneController(mainStackPaneController);
            /** Ustawienie okna serviceDetails */
            mainStackPaneController.setScreen(stackPane);
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
    }
    /**
     * Metoda ustawia wartości które mają się wyświetlać w poszczególnych kolumnach
     */
    private void setUpColumns() {
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        statusColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());
    }

    /**
     * Metoda ustawia liste zleceń.
     */
    private void setUpServiceList() {
        servicesFxList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesFxList);
    }

    @FXML
    void initialize() {
        System.out.println("Wersja dla konta: "+mainStackPaneController.accountType);

        this.setUpColumns();
        this.setUpServiceList();
        this.loadServices();
        /** zarządzanie dostępem przycisków */
        buttonManagment();
        appendUsersToUsersFx();

        /** ukrywanie elementów dla kont bez uprawnień */
        switch (mainStackPaneController.accountType){
            case A:
                tasksTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case K:
                adminPanelTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case M:
                tasksTab.setDisable(true);
                adminPanelTab.setDisable(true);
                addNewServiceButton.setVisible(false);
                invoicePDFButton.setVisible(false);
                break;
            case R:
                adminPanelTab.setDisable(true);
                tasksTab.setDisable(true);
                break;
            case ALL:
                invoicePDFButton.setVisible(true);
                showDetailsButton.setVisible(true);
                break;
        }
    }

    /**
     * Metoda pobiera serwisy z bazy danych.
     */
    public void loadServices() {
        servicesList = serviceService.findAll();
    }

    /**
     * Metoda zamienia użytkwonika na użytkownika Fx i dodaje go do tablicy servicesFxList
     */
    public void appendUsersToUsersFx() {
        if (!servicesList.isEmpty()) {
            for (Service service : servicesList) {
                ServiceFx serviceFx = new ServiceFx(service.getCar(), service.getClient(), service.getStatus());
                servicesFxList.add(serviceFx);
            }
        }
        buttonManagment();
    }
    /**
     * Metoda wyłącza dostęp do przycisków Recepcjonisty gdy nie ma żadnych zleceń w "bazie danych"
     */
    void buttonManagment(){
        if(servicesFxList.isEmpty()){
            invoicePDFButton.setDisable(true);
            showDetailsButton.setDisable(true);
        }
        else {
            invoicePDFButton.setDisable(false);
            showDetailsButton.setDisable(false);
        }
    }
}