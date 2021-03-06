package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import myPck.database.models.Service;
import myPck.services.ServiceService;

import java.io.IOException;

public class ServiceDetailsController extends Controller {

    /**
     * opisywany serwis
     */
    boolean editMode = false;
    private Service service = null;
    private ServiceService serviceService;

    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Pole z informacją o samochodzie
     */
    @FXML
    private Label carLabel;
    @FXML
    private Button editSaveDescButton;
    @FXML
    //pole z informacją o kliencie
    private Label customerLabel;
    @FXML
    //etykieta inforująca o stanie zlecenia np: Done, in ServiceFx, not allocated
    private Label statusLabel;

    @FXML
    //opis zlecenia
    private TextArea faultDescTextArea;

    /**
     * Opis raportu
     */
    @FXML
    private TextArea repairDescTextArea;

    //przyciski
    @FXML
    private Button addReportButton;

    @FXML
    private Button backButton;

    public Button editReportButton;

    /**
     * Metoda ładuje widok podglądu usługi i przekazuje główny kontroler.
     *
     * @param event
     */
    @FXML
    void loadAddServiceReportView(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ServiceReport.fxml"));
        Pane pane;
        try {
            pane = loader.load();
            ServiceReportController serviceReportController = loader.getController();
            serviceReportController.setMainStackPaneController(mainStackPaneController);
            serviceReportController.setService(this.service);
            mainStackPaneController.setScreen(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ładuje główne okno aplikacji
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void backToMainWindow(ActionEvent event) throws IOException {
        //załadowanie głównego okna
        mainStackPaneController.loadMainWindow();
    }

    @FXML
    void initialize() {
        serviceService = new ServiceService();

        //ukrywanie elementów dla kont bez uprawnień
        switch (mainStackPaneController.accountType) {
            case ALL:
            case M:
                addReportButton.setVisible(true);
                break;
            default:
                addReportButton.setVisible(false);
        }
    }

    private void manageButtons() {
        if (this.service.getServiceReport() != null) {
            addReportButton.setVisible(false);
            editReportButton.setVisible(true);
        }
    }
    /**
     * Wypełnia okno danymi i ustawia odpowiedni przycisk
     */
    public void setData() {

        faultDescTextArea.setText(this.service.getDescription());
        carLabel.setText(this.service.getCar());
        customerLabel.setText(this.service.getClient());
        statusLabel.setText(this.service.getStatus());
        if (this.service.getServiceReport() != null) {
            repairDescTextArea.setText(this.service.getServiceReport().getDescription());
        }
        /** ustawienie koloru dla statusu */
        setCollorOfStatus();
        manageButtons();
        if (service.getStatus().equals("Not allocated")) {
            editSaveDescButton.setVisible(true);
        } else {
            editSaveDescButton.setVisible(false);
        }

    }

    /**
     * Ustawia kolor dla obiektu statusLabel
     */
    private void setCollorOfStatus() {
        String s = statusLabel.getText();

        if (s.equals("Done")) {
              statusLabel.setTextFill(Color.web("#00ff00"));
        }
        if (s.equals("In service")) {
            statusLabel.setTextFill(Color.web("#ff0000"));
        }
        if (s.equals("No allocated")) {
            statusLabel.setTextFill(Color.web("#ffbf00"));
        }
    }

    public void loadEditServiceReportView(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ServiceReport.fxml"));
        Pane pane;
        try {
            pane = loader.load();
            ServiceReportController serviceReportController = loader.getController();
            serviceReportController.setMainStackPaneController(mainStackPaneController);
            serviceReportController.setEditMode(true);
            serviceReportController.setService(this.service);
            serviceReportController.setUpWindow();
            mainStackPaneController.setScreen(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editSaveDesc(ActionEvent event) {

        if (editMode == false) {
            System.out.println("test");
            faultDescTextArea.setEditable(true);
            faultDescTextArea.requestFocus();
            editSaveDescButton.setText("Save");
            editMode = true;
        } else {
            service.setDescription(faultDescTextArea.getText());
            serviceService.update(service);
            editSaveDescButton.setText("Edit");
            faultDescTextArea.setEditable(false);
            editMode = false;
        }
    }
}
