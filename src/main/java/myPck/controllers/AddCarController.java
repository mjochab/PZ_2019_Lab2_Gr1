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
    private Button addButton;

    @FXML
    private Button backButton;


    @FXML
    void add(ActionEvent actionEvent) throws IOException {
        String model = modelField.getText();
        String brand = brandField.getText();
        String type = typeField.getText();
        if (model.isEmpty() || brand.isEmpty() || type.isEmpty()) {
            System.out.println("Error");
        }else{
            this.car = new Car(model,brand,type);
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
        carService = new CarService();
    }
    public Car car;
    public CarFx carFx;
    public CarService carService;


}
