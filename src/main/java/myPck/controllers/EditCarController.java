package myPck.controllers;

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

import java.io.IOException;

public class EditCarController extends Controller  {
    @FXML
    private javafx.scene.control.TextField ModelField;
    @FXML
    private javafx.scene.control.TextField BrandField;
    @FXML
    private  TextField TypeField;
    @FXML
    private TextField ProductionsDateField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    void initialize () {
        Validator.setMaxLengthOfTextField(ModelField,100);
        Validator.setMaxLengthOfTextField(BrandField,100);
        Validator.setMaxLengthOfTextField(TypeField,100);
        Validator.setMaxLengthOfTextField(ProductionsDateField, 4);
        Validator.convertTextFieldToNumberField(ProductionsDateField);
        carService = new CarService();
    }
    private Car car ;
    private  CarFx carFx;
    private CarService carService;

    public void  setCar(Car car) {
        this.car = car;
        this.carFx = convertCartoCarFx(car);
        setFieldsBinding();
    }
    private static CarFx convertCartoCarFx(Car car) {
        return new CarFx(car.getModel(),car.getBrand(),car.getType(),car.getPrductionsDate().toString());
    }


    private void setFieldsBinding(){
        ModelField.textProperty().bindBidirectional(carFx.ModelProperty());
        BrandField.textProperty().bindBidirectional(carFx.BrandProperty());
        TypeField.textProperty().bindBidirectional(carFx.TypeProperty());
        ProductionsDateField.textProperty().bindBidirectional(carFx.ProductionsDateProperty());
    }
    @FXML
    void save(ActionEvent event) {
        car.setModel(carFx.getModel());
        car.setBrand(carFx.getBrand());
        car.setType(carFx.getType());
        car.setPrductionsDate(carFx.getProductionsDate());
        String Model = car.getModel();
        String Brand = car.getBrand();
        String Type = car.getType();
        String ProductionsDate = car.getPrductionsDate().toString();

        if (Model.isEmpty() || Brand.isEmpty() || Type.isEmpty() || ProductionsDate.isEmpty() || ProductionsDate.length() !=4) {
            System.out.println("Error");
        } else {
            carService.update(car);
            cancelButton.fire();
        }
    }
        @FXML
            void back (ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
            StackPane stackPane = loader.load();

            /** przekazanie kontrolera (głównego okna) do okienka AddService */
            AddServiceController addServiceController = loader.getController();
            addServiceController.setMainStackPaneController(mainStackPaneController);
            /** Ustawienie okna AddService */
            mainStackPaneController.setScreen(stackPane);


        }
    }


