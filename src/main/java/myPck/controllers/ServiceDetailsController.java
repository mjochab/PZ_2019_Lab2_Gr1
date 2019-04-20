package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import myPck.database.models.Service;

import java.io.IOException;

public class ServiceDetailsController extends Controller{

    /** opisywany serwis */
    private Service service = null;

    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Pole z informacją o samochodzie
     */
    @FXML
    private TextField carTextField;

    @FXML
    //pole z informacją o kliencie
    private TextField customerTextField;
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

    /**
     * Metoda ładuje widok podglądu usługi i przekazuje główny kontroler.
     * @param event
     */
    @FXML
    void loadServiceReportView(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddServiceReport.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();

            ServiceReportController serviceReportController = loader.getController();
            serviceReportController.setMainStackPaneController(mainStackPaneController);

            mainStackPaneController.setScreen(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ładuje główne okno aplikacji
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
        //ukrywanie elementów dla kont bez uprawnień
        switch (mainStackPaneController.accountType){
            case ALL:
            case M:
                addReportButton.setVisible(true);
                break;
                default:
                    addReportButton.setVisible(false);
        }

    }

    /**
     * Wypełnia formatki w oknie przykładowymi danymi
     */
    public void setData(){

        faultDescTextArea.setText(this.service.getDescription());
        carTextField.setText(this.service.getCar());
        customerTextField.setText(this.service.getClient());
        statusLabel.setText(this.service.getStatus());
        /** ustawienie koloru dla statusu */
        setCollorOfStatus();
    }

    /**
     * Ustawia kolor dla obiektu statusLabel
     */
    private void setCollorOfStatus(){
        String s = statusLabel.getText();

        if(s=="Done"){
            statusLabel.setTextFill(Color.web("#00ff00"));
        }
        if(s=="In service"){
            statusLabel.setTextFill(Color.web("#ff0000"));
        }
        if(s=="No allocated"){
            statusLabel.setTextFill(Color.web("#ffbf00"));
        }

    }
}
