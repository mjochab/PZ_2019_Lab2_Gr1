package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.utils.Validator;
import myPck.database.models.Car;
import myPck.modelsFx.CarFx;
import myPck.services.CarService;

import java.io.IOException;

public class AddEditCarController extends Controller {

    @FXML
    private Label infoModel;
    @FXML
    private Label infoBrand;
    @FXML
    private Label infoType;
    @FXML
    private Label infoDate;
    @FXML
    private TextField modelField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField productionDate;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        coverInfoLabels();
        Validator.setMaxLengthOfTextField(modelField, 100);
        Validator.setMaxLengthOfTextField(brandField, 100);
        Validator.setMaxLengthOfTextField(typeField, 100);
        Validator.setMaxLengthOfTextField(productionDate, 4);
        Validator.convertTextFieldToNumberField(productionDate);
        carService = new CarService();
    }

    private Car car = null;
    private CarFx carFx;
    private CarService carService;

    public void setCar(Car car) {
        this.car = car;
        this.carFx = convertCartoCarFx(car);
        setFieldsBinding();
    }

    private static CarFx convertCartoCarFx(Car car) {
        return new CarFx(car.getModel(), car.getBrand(), car.getType(), car.getPrductionsDate().toString());
    }


    private void setFieldsBinding() {
        modelField.textProperty().bindBidirectional(carFx.ModelProperty());
        brandField.textProperty().bindBidirectional(carFx.BrandProperty());
        typeField.textProperty().bindBidirectional(carFx.TypeProperty());
        productionDate.textProperty().bindBidirectional(carFx.ProductionsDateProperty());
    }
    void coverInfoLabels(){
        infoBrand.setVisible(false);
        infoDate.setVisible(false);
        infoModel.setVisible(false);
        infoType.setVisible(false);
    }
    void edit() {
        car.setModel(carFx.getModel());
        car.setBrand(carFx.getBrand());
        car.setType(carFx.getType());
        car.setPrductionsDate(carFx.getProductionsDate());
        String model = car.getModel();
        String brand = car.getBrand();
        String type = car.getType();
        String ProductionsDate = car.getPrductionsDate().toString();

        if (model.isEmpty() || brand.isEmpty() || type.isEmpty() || ProductionsDate.isEmpty() || ProductionsDate.length() != 4) {
            coverInfoLabels();
            if(model.isEmpty()) infoModel.setVisible(true);
            if(brand.isEmpty()) infoBrand.setVisible(true);
            if(type.isEmpty()) infoType.setVisible(true);
            if(ProductionsDate.isEmpty()) infoDate.setVisible(true);
            if(ProductionsDate.length() !=4) infoDate.setVisible(true);
        } else {
            carService.update(car);
            backButton.fire();
        }
    }

    void addNew() {
        String model = modelField.getText();
        String brand = brandField.getText();
        String type = typeField.getText();
        String ProductionsDate = productionDate.getText();
        if (model.isEmpty() || brand.isEmpty() || type.isEmpty() || ProductionsDate.isEmpty() || ProductionsDate.length() != 4) {
            coverInfoLabels();
            if(model.isEmpty()) infoModel.setVisible(true);
            if(brand.isEmpty()) infoBrand.setVisible(true);
            if(type.isEmpty()) infoType.setVisible(true);
            if(ProductionsDate.isEmpty()) infoDate.setVisible(true);
            if(ProductionsDate.length() !=4) infoDate.setVisible(true);
        } else {
            this.car = new Car(model, brand, type, ProductionsDate);
            carService.persist(this.car);
            backButton.fire();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (this.car != null){
            edit();
        }
        else{
            addNew();
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
}

