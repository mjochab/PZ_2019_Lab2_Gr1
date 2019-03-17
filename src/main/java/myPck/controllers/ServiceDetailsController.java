package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import myPck.Service;

import java.io.IOException;

public class ServiceDetailsController {

    //instancja kontrolera zewnętrzenego okna (rodzica)
    private MainStackPaneController mainStackPaneController;
    //funkcja ustawiająca kontroller
    public void setMainStackPaneController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    @FXML
    //pole z informacją o samochodzie
    private TextField carTextField;
    @FXML
    //pole z informacją o kliencie
    private TextField customerTextField;
    @FXML
    //etykieta inforująca o stanie zlecenia np: Done, in Service, not allocated
    private Label statusLabel;

    @FXML
    //opis zlecenia
    private TextArea faultDescTextArea;

    @FXML
    //opis raportu
    private TextArea repaiDescTextArea;

    //przyciski
    @FXML
    private Button addRaportButton;
    @FXML
    private Button backButton;

    /**
     *
     * @param event
     */
    @FXML
    void addRaport(ActionEvent event) {

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
        //wypełnienie pól przykładowymi danymi
        setSampleData();

        //ukrywanie elementów dla kont bez uprawnień
        switch (mainStackPaneController.ACCOUNT){
            case M:
                addRaportButton.setVisible(true);
                break;
                default:
                    addRaportButton.setVisible(false);
        }

    }

    /**
     * Wypełnia formatki w oknie przykładowymi danymi
     */
    private void setSampleData(){

        String sampleDesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Nam non volutpat nibh, vel tempor dolor. In at dui nec mauris imperdiet" +
                " dictum vitae ac odio. In quis nunc at quam rutrum placerat tristique sit" +
                " amet turpis. Nulla facilisi. In ullamcorper vehicula est vel egestas. Quisque" +
                " ac nibh eu velit volutpat luctus. Vivamus imperdiet est eget dolor interdum, nec" +
                " maximus risus facilisis. Etiam auctor mi urna, id commodo ligula condimentum" +
                " porttitor. Phasellus ut pharetra lectus, eget auctor elit. Donec eget semper dui." +
                " Sed ut justo consequat, consequat metus id, tincidunt quam." +
                " Suspendisse luctus ante sodales, euismod tellus fringilla, venenatis velit." +
                " Duis ex nulla, eleifend ac velit ac, porta sagittis orci. Sed quis dui nibh.Sed" +
                " neque nisi, egestas sit amet venenatis eget, ultricies id felis. Etiam efficitur" +
                " tortor eget nibh malesuada, vitae volutpat massa finibus. Pellentesque a sodales " +
                "libero. Ut non lacinia nulla. Donec in dui ipsum. Pellentesque et viverra tortor." +
                " Curabitur posuere magna at suscipit aliquet. Duis risus tellus, laoreet id tempus " +
                "a, vehicula sed enim. Nullam leo diam, imperdiet sed orci sit amet, convallis finib" +
                "us tortor. Phasellus non tristique eros, sed malesuada dui. Duis scelerisque erat ve" +
                "l lectus tempor hendrerit. Interdum et malesuada fames ac ante ipsum primis in fauci" +
                "bus. Mauris dapibus dolor eget mauris placerat sollicitudin. Cras posuere mattis neq" +
                "ue, vel finibus risus.Morbi ut justo porta, fermentum urna vitae, semper enim. Duis e" +
                "u elementum felis. Cras dapibus augue at iaculis rhoncus. Donec eget augue risus. Mor" +
                "bi justo purus, gravida quis odio et, imperdiet pellentesque justo. Praesent sem ligu" +
                "la, ornare non ex id, placerat volutpat tortor. Morbi id tellus at tellus eleifend gra" +
                "vida sit amet et nisl. Curabitur dapibus sem sit amet mauris cursus scelerisque. Fusce " +
                "at posuere nisl, vel facilisis erat. Proin ut justo at justo sollicitudin convallis vel non nibh.";

        Service service = new Service("Jagura FX", "Jan Kowalski", "Done");

        faultDescTextArea.setText(sampleDesc);
        carTextField.setText(service.getCar());
        customerTextField.setText(service.getClient());
        statusLabel.setText(service.getStatus());
        //ustawienie koloru dla statusu
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
        if(s=="In repair"){
            statusLabel.setTextFill(Color.web("#ff0000"));
        }
        if(s=="No allocated"){
            statusLabel.setTextFill(Color.web("#ffbf00"));
        }

    }
}
