package myPck.controllers;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.controllers.utils.Validator;
import myPck.database.models.Car;
import myPck.modelsFx.CarFx;
import myPck.services.CarService;

public class AddCarController extends Controller{


    @FXML
    private TextField modelField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField typeField;
    @FXML
    private TextField ProductionsDateField;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;


    @FXML
    void add(ActionEvent actionEvent) throws IOException {
        String model = modelField.getText();
        String brand = brandField.getText();
        String type = typeField.getText();
        String ProductionsDate = ProductionsDateField.getText();
        if (model.isEmpty() || brand.isEmpty() || type.isEmpty() || ProductionsDate.isEmpty() || ProductionsDate.length() !=4) {
            System.out.println("Error");
        }else{
            this.car = new Car(model,brand,type,ProductionsDate);
            carService.persist(this.car);
            backButton.fire();
        }

        }
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = loader.load();
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(stackPane);
    }
    @FXML
    void initialize() {
        Validator.setMaxLengthOfTextField(modelField,200);
        Validator.setMaxLengthOfTextField(brandField,200);
        Validator.setMaxLengthOfTextField(typeField,100);
        Validator.convertTextFieldToNumberField(ProductionsDateField);
        carService = new CarService();
    }
    public Car car;
    public CarFx carFx;
    public CarService carService;


}
