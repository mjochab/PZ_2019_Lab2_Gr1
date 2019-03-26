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

import java.io.IOException;

public class MainWindowController {

    /**
     * Instancja kontrolera zewnętrzenego okna (rodzica)
     */
    private MainStackPaneController mainStackPaneController;

    /**
     * Ustawia kontroler
     * @param mainStackPaneController
     */
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

    //lista zawierająca zlecenia
    private ObservableList<Service> servicesList;

    @FXML
    //tableka wyświetlająca dane z listy ze zleceniami
    private TableView<Service> servicesTableView;

    @FXML
    //kolumna zawierająca informacje o samochodzie
    private TableColumn<Service, String> carColumn;

    @FXML
    //kolumna zawierająca informacje o kliencie
    private TableColumn<Service, String> clientColumn;

    @FXML
    //kolumna zawierająca informacje o statusie zlecenia
    private TableColumn<Service, String> statusColumn;
    //przyciski
    @FXML
    private Button addNewServiceButton;
    @FXML
    private Button invoicePDFButton;
    @FXML
    private Button showDetailsButton;

    //zakładki
    @FXML
    private Tab profileTab;
    @FXML
    private Tab adminPanelTab;
    @FXML
    private Tab tasksTab;

    /**
     * ta funkcja w przyszłości będzie otwierać nowe okno (panel dodawanie zlecenia)
     * @param event
     */

    @FXML
    //ta funkcja w przyszłości będzie otwierać nowe okno (panel dodawanie zlecenia)
    void addServicesTest(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        //przekazanie kontrolera (głównego okna) do okienka AddService
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        //ustawienie okna AddService
        mainStackPaneController.setScreen(stackPane);
    }

    /**
     * generowanie faktury
     * @param event
     */
    @FXML
    void invoicePDFTest(ActionEvent event) {
        Service service;
        try{
            //sprawdza czy zaznaczono jakiś element w TableView
            service = servicesTableView.getSelectionModel().getSelectedItem();
            System.out.println("Generuje PDF dla:");
            System.out.println(service.getCar());
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
    }

    /**
     * Otwiera okno ze szczególowymi informacjami o wybranym zleceniu
     * @param event
     * @throws IOException
     */
    @FXML
    void showDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ServiceDetails.fxml"));
        StackPane stackPane = loader.load();
        Service service;
        try{
            //sprawdza czy zaznaczono jakieś zlecenie w TableView
            service = servicesTableView.getSelectionModel().getSelectedItem();
            System.out.println("Pokazuje detale dla zlecenia z samochodem : ");
            System.out.println(service.getCar());

            //przekazanie kontrolera (głównego okna) do okienka serviceDetails
            ServiceDetailsController serviceDetailsController = loader.getController();
            serviceDetailsController.setMainStackPaneController(mainStackPaneController);
            //ustawienie okna serviceDetails
            mainStackPaneController.setScreen(stackPane);
        }catch(Exception e){
            System.out.println("Nie wybrano niczego");
        }
    }


    @FXML
    void initialize() {
        System.out.println("Wersja dla konta: "+mainStackPaneController.ACCOUNT);

        servicesList = FXCollections.observableArrayList();
        servicesTableView.setItems(this.servicesList);

        //ustawienie wartości które mają się wyświetlać w poszczególnych kolumnach
        carColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        statusColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());

        //zarządzanie dostępem przycisków
        buttonManagment();

        //ukrywanie elementów dla kont bez uprawnień
        switch (mainStackPaneController.ACCOUNT){
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
    /**
     * wypełnienie listy przykładowymi zleceniami
     */
    void sampleData(){

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
    /**
     * funkcja wyłącza dostęp do przycisków Recepcjonisty gdy nie ma żadnych zleceń w "bazie danych"
     */
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
