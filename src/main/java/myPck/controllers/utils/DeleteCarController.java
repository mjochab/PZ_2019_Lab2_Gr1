package myPck.controllers.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.controllers.AddServiceController;
import myPck.controllers.Controller;
import myPck.database.models.Car;
import myPck.modelsFx.CarFx;
import myPck.services.CarService;
import java.io.IOException;
import java.util.Date;


public class DeleteCarController extends Controller {
    @FXML
    private TextField ModelField;
    @FXML
    private TextField BrandField;
    @FXML
    private TextField TypeField;
    @FXML
    private TextField ProductionsDateField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;


    void initialize() {
        Validator.setMaxLengthOfTextField(ModelField, 200);
        Validator.setMaxLengthOfTextField(BrandField, 200);
        Validator.setMaxLengthOfTextField(TypeField, 50);
        Validator.convertTextFieldToNumberField(ProductionsDateField);
        Validator.setMaxLengthOfTextField(ProductionsDateField, 10);
        carService = new CarService();
    }

    private Car car;
    private CarFx carFx;
    private CarService carService;

    public void setCar(Car car) {
        this.car = car;
        this.carFx = converCartoCarFx(car);
        setFieldsBinding();
    }

    private CarFx converCartoCarFx(Car car) {
        return new CarFx(car.getModel(), car.getBrand(), car.getType(), car.getProductionsDate());
    }

    private void setFieldsBinding() {
        ModelField.textProperty().bindBidirectional(carFx.ModelProperty());
        BrandField.textProperty().bindBidirectional(carFx.BrandProperty());
        TypeField.textProperty().bindBidirectional(carFx.TypeProperty());
        ProductionsDateField.textProperty().bindBidirectional(carFx.ProductionsDateProperty());
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        /** zmiana parametrów Samochodu na nowe */
        car.setModel(carFx.getModel());
        car.setBrand(carFx.getBrand());
        car.setType(carFx.getType());
        car.setProductionsDate(carFx.getProductionsDate());

        String Model = car.getModel();
        String Branch = car.getBrand();
        String Type = car.getType();
        String ProductionsDate = String.valueOf(car.getProductionsDate());
        if (Model.isEmpty() || Branch.isEmpty() || Type.isEmpty() || ProductionsDate.length() != 10) {
            System.out.println("Error");
        } else {
            /** zapis do bazy danych */
            carService.update(car);
            /** wywołanie przycisku powrotu */
            cancelButton.fire();
        }
    }
    @FXML
    void Cancel (ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AddService.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** przekazanie kontrolera (głównego okna) do okienka AddService */
        AddServiceController addServiceController = loader.getController();
        addServiceController.setMainStackPaneController(mainStackPaneController);
        /** Ustawienie okna AddService */
        mainStackPaneController.setScreen(stackPane);
    }

}

