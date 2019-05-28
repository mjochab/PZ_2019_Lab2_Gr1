package myPck.controllers;

import com.itextpdf.text.DocumentException;
import com.rejman.Invoice;
import com.rejman.Person;
import com.rejman.PositonOfInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.database.models.*;
import myPck.modelsFx.ServiceFx;
import myPck.services.InvoiceService;
import myPck.services.ServiceService;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainWindowController extends Controller {

    /**
     * Serwis Zleceń
     */
    private ServiceService serviceService;
    private InvoiceService invoiceService;
    public User user;
    /**
     * Konstruktor MainWindowControlle i inicjalizacja Serwisu zleceń
     */
    public MainWindowController() {
        this.serviceService = new ServiceService();
        this.invoiceService = new InvoiceService();
    }

    /**
     * opcje filtrowania
     */
    private ObservableList<String> options;
    /**
     * lista zawierająca serwisy
     */
    private List<Service> servicesList;
    /**
     * kopia listy wykorzystywana przy filtrowaniu
     */
    private List<Service> servicesListCopy;
    /**
     * Lista zawierająca serwisyFx
     */
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

    /**
     * Przyciski
     */
    @FXML
    private Button addNewServiceButton;
    @FXML
    private Button invoicePDFButton;
    @FXML
    private Button showDetailsButton;

    /**
     * Zakładki
     */
    @FXML
    private Tab profileTab;
    @FXML
    private Tab adminPanelTab;
    @FXML
    private Tab tasksTab;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> filterComboBox;

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void addService(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        /** przekazanie kontrolera (głównego okna) do okienka AddService */
        ServiceController serviceController = loader.getController();
        serviceController.setMainStackPaneController(mainStackPaneController);
        /** Ustawienie okna AddService */
        mainStackPaneController.setScreen(stackPane);
    }
    @FXML
    void deleteService(ActionEvent event){
        if (!servicesFxList.isEmpty()) {
            int id = servicesTableView.getSelectionModel().getSelectedIndex();
            Service selected = servicesList.get(id);

            boolean isDelete = serviceService.delete(selected.getId());

            if (isDelete) {
                System.out.println("Usunięto");
                servicesList.clear();
                loadServices();
                servicesFxList.clear();
                appendServiceToServiceFx();
            } else {
                System.out.println("Nie usunięto");
            }
        }
    }

    /**
     * generowanie faktury
     * @param event
     */

    private Date setInvoiceDate(Service service){
        myPck.database.models.Invoice invoice = service.getInvoice();
        if(invoice==null){
            Date date =  new Date();
            invoice = new myPck.database.models.Invoice();
            invoice.setDate_of_issue(date);
            service.setInvoice(invoice);
            invoiceService.persist(invoice);
            serviceService.update(service);
            return date;
        }else{
            return invoice.getDate_of_issue();
        }
    }
    @FXML
    void invoicePDF(ActionEvent event) throws IOException, DocumentException, ClassNotFoundException {
        Service selected;
        Company company = Company.readObjectFromFile("company.txt");


        try{
            int id = servicesTableView.getSelectionModel().getSelectedIndex();
            selected = servicesList.get(id);
            if(selected.getStatus().equals("Done")){

                String path = "invoices/";
                Date date = setInvoiceDate(selected);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String title = "invoice "+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
                //Dane firmy wystawiającej fakture

                Person dealer = new Person(company.getName(),company.getAddress(),company.getNip());
                Person buyer;

                Client client = selected.getClientInstance();
                String name = client.getFirstName()+" "+client.getLastName();
                buyer = new Person(name,client.getAddress(),client.getNipNumber());
                List<ServicePart> list = selected.getServiceParts();
                PositonOfInvoice[] rows = new PositonOfInvoice[list.size()];

                for(int i=0;i<rows.length;i++){
                    rows[i] = new PositonOfInvoice(list.get(i).getName(), list.get(i).getPrice());
                }
                Invoice invoice = new Invoice(title,dealer,buyer,rows);
                path+=title+" "+name+".pdf";
                invoice.createDocument(path);

                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Information");
                alert.setHeaderText("Invoice was created.");
                alert.setContentText("Open the invoice.");
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().addAll(yes, no);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == yes) {
                    openInvoice(path);
                }


            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText("Invoice can't be generated for this service.");
                alert.setContentText("The service has got to be done.");

                alert.showAndWait();
            }



        }catch(Exception ex){

        }


    }
    private void openInvoice(String path){
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void showDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ServiceDetails.fxml"));
        StackPane stackPane = loader.load();
        Service selected;
        try {
            /** sprawdza czy zaznaczono jakieś zlecenie w TableView */
            int id = servicesTableView.getSelectionModel().getSelectedIndex();
            selected = servicesList.get(id);

            ServiceDetailsController serviceDetailsController = loader.getController();
            /** przekazanie zaznaczonego serwisu */
            serviceDetailsController.setService(selected);
            /** wyswietlenie danych o serwisie */
            serviceDetailsController.setData();

            serviceDetailsController.setMainStackPaneController(mainStackPaneController);
            /** Ustawienie okna serviceDetails */
            mainStackPaneController.setScreen(stackPane);
        } catch (Exception e) {
            System.out.println("Nie wybrano niczego");
        }
    }

    /**
     * Metoda ustawia wartości które mają się wyświetlać w poszczególnych kolumnach
     */
    private void setUpColumns() {
        carColumn.setCellValueFactory(cellData -> cellData.getValue().carProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
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
        System.out.println("Wersja dla konta: " + mainStackPaneController.accountType);
        options = FXCollections.observableArrayList();
        options.setAll("All", "Done", "In service", "Not allocated", "car", "client");
        filterComboBox.setItems(options);
        filterComboBox.getSelectionModel().selectFirst();

        this.setUpColumns();
        this.setUpServiceList();
        this.loadServices();

        /** zarządzanie dostępem przycisków */
        buttonManagment();
        appendServiceToServiceFx();
        searchField.setVisible(false);
        searchButton.setVisible(false);
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
        servicesListCopy = new ArrayList<Service>(servicesList);
    }

    /**
     * Metoda zamienia użytkwonika na użytkownika Fx i dodaje go do tablicy servicesFxList
     */
    public void appendServiceToServiceFx() {
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
    void buttonManagment() {
        if (servicesFxList.isEmpty()) {
            invoicePDFButton.setDisable(true);
            showDetailsButton.setDisable(true);
        } else {
            invoicePDFButton.setDisable(false);
            showDetailsButton.setDisable(false);
        }
    }

    @FXML
    void search(ActionEvent event) {
        servicesList.clear();
        servicesFxList.clear();
        String searchValue = searchField.getText().toLowerCase();
        String value;
        String checkCategory = filterComboBox.getSelectionModel().getSelectedItem();
        for (Service service : servicesListCopy) {
            switch (checkCategory) {
                case "car":
                    value = service.getCar();
                    break;
                case "client":
                    value = service.getClient();
                    break;
                default:
                    value = null;
            }
            value = value.toLowerCase();
            if (value.indexOf(searchValue) >= 0) {
                servicesList.add(service);
            }
        }
        appendServiceToServiceFx();
    }

    @FXML
    void comboBoxAction(ActionEvent event) {
        String option = filterComboBox.getSelectionModel().getSelectedItem();
        servicesList.clear();
        servicesFxList.clear();
        switch (option) {
            case "All":
                showAll();
                break;
            case "car":
            case "client":
                showSearched();
                break;
            default: {
                showStatus(option);
            }
        }
    }

    private void showAll() {
        searchButton.setVisible(false);
        searchField.setVisible(false);

        servicesList = new ArrayList<>(servicesListCopy);
        appendServiceToServiceFx();
    }

    private void showStatus(String status) {
        searchButton.setVisible(false);
        searchField.setVisible(false);

        for (Service service : servicesListCopy) {
            if (service.getStatus().equals(status)) {
                servicesList.add(service);
            }
        }
        appendServiceToServiceFx();
    }

    private void showSearched() {
        searchButton.setVisible(true);
        searchField.setVisible(true);
        searchField.setText("");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}