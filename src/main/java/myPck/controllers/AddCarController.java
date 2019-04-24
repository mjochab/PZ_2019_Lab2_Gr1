package myPck.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import myPck.controllers.utils.Validator;
import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.modelsFx.CarFx;
import myPck.services.CarService;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.services.ClientService;
import java.util.Date;


public class AddCarController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ModelField;

    @FXML
    private TextField BrandField;

    @FXML
    private TextField TypeField;

    @FXML
    private TextField ProductionsDateField;

    @FXML
    private Button addButton;
    @FXML
    private Button CancelButton;

    @FXML
    void initialize() {

        Validator.setMaxLengthOfTextField(ModelField, 200);
        Validator.setMaxLengthOfTextField(BrandField, 200);
        Validator.setMaxLengthOfTextField(TypeField, 10);
        Validator.setMaxLengthOfTextField(ProductionsDateField, 50);
        Validator.convertTextFieldToNumberField(ProductionsDateField);
        carService = new CarService();
    }
    private Car car;
    private CarFx carFx;
    private CarService carService;

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(stackPane);
    }


    @FXML
    void add(ActionEvent event) throws IOException {
        String Model = ModelField.getText();
        String Brand = BrandField.getText();
        String Type = TypeField.getText();
        String ProductionsDate = ProductionsDateField.getText();

        if (Model.isEmpty() || Brand.isEmpty() || Type.isEmpty() ||  ProductionsDate.length() != 10) {
            System.out.println("Error");
        } else {

            this.car = new Car(Model, Brand, Type, ProductionsDate);
            carService.persist(this.car);
            /** wywołanie przycisku powrotu */
            CancelButton.fire();
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();

        //przekazanie kontrolera (głównego okna) do okienka serviceDetails
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        //ustawienie okna serviceDetails
        mainStackPaneController.setScreen(stackPane);
    }
}
